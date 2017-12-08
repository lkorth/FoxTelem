package telemetry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;

import common.Config;
import common.Spacecraft;
import decoder.FoxBitStream;
import decoder.FoxDecoder;

/**
 * 
 * FOX 1 Telemetry Decoder
 * @author chris.e.thompson g0kla/ac2cz
 *
 * Copyright (C) 2015 amsat.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
public class PayloadRadExpData extends FoxFramePart {
	
	public static final int MAX_PAYLOAD_RAD_SIZE = 58;
	
	public PayloadRadExpData(BitArrayLayout lay) {
		super(lay);
//		MAX_BYTES = MAX_PAYLOAD_RAD_SIZE;
		rawBits = new boolean[MAX_BYTES*8];
		
	}
	
	public PayloadRadExpData(int id, int resets, long uptime, String date, StringTokenizer st, BitArrayLayout lay) {
		super(id, resets, uptime, date, st, lay);
//		MAX_BYTES = MAX_PAYLOAD_RAD_SIZE;
	}

	public PayloadRadExpData(ResultSet r, BitArrayLayout lay) throws SQLException {
		super(r, lay);
	}
	
	protected void init() {
		fieldValue = new int[MAX_PAYLOAD_RAD_SIZE];
		type = TYPE_RAD_EXP_DATA;
	}
	
	/*
	public void copyBitsTo58Fields() {
		resetBitPosition();
		for (int i =0; i< MAX_PAYLOAD_RAD_SIZE; i++)
			fieldValue[i] = nextbits(8);	
	}
*/
	
	
	/**
	 * For Fox 1-A and Fox-1C If byte 21 onwards is zero then this is telemetry.  Zeros are not allowed in the packet format because of the
	 * COBS routine.  So if we find zeros, this is telemetry
	 * Fox-1D has Housekeeping telemetry.  We can never confuse this with packets.  So return true.
	 * Fox-1B fields 11 - 20 are zero (because there is no VUC Exp 1.  We also have not tested or used packets, so assume true
	 * @return
	 */
	public boolean isTelemetry() {
		if (id == Spacecraft.FOX1D) {
			return true;
		} else
		if (id == Spacecraft.FOX1B) {
			return true;
		} else // id = 1 or 3
			for (int i=21; i < 25; i++)
				if (fieldValue[i] != 0) return false;
		
		return true;
	}
	
	/**
	 * Calculate the telemetry and return it
	 * @return
	 */
	public RadiationTelemetry calculateTelemetryPalyoad() {
		//if (isTelemetry()) {
		if (id == Spacecraft.FOX1E) {
			// We cheat because the layout has each experiment 10 bytes appart but the 10 comes in the same 10 bytes.  This is in contrast to 1A and 1B
			RadiationTelemetry radTelem = new RadiationTelemetry(resets, uptime, Config.satManager.getLayoutByName(id, Spacecraft.RAD2_LAYOUT));
			for (int k=0; k<10; k++) { // add the first 10 bytes 
				radTelem.addNext8Bits(fieldValue[k]);
			}
			radTelem.copyBitsToFields();
			int offset=0;
			if (radTelem.getRawValue("State2") == 3)  // 3 = Active
				offset=10;
			else if (radTelem.getRawValue("State3") == 3)
				offset=20;
			else if (radTelem.getRawValue("State4") == 3)
				offset=30;

			// Pretend there is a gap, so that the layout works like Fox-1B
			for (int k=10; k<10+offset; k++) { 
				radTelem.addNext8Bits(0);
			}
			// Now flow the rest of the data in
			for (int k=10+offset; k<RadiationTelemetry.MAX_RAD_TELEM_BYTES; k++) { 
				radTelem.addNext8Bits(fieldValue[k]);
			}
			return radTelem;
		} else {
			RadiationTelemetry radTelem = new RadiationTelemetry(resets, uptime, Config.satManager.getLayoutByName(id, Spacecraft.RAD2_LAYOUT));
			for (int k=0; k<RadiationTelemetry.MAX_RAD_TELEM_BYTES; k++) { 
				radTelem.addNext8Bits(fieldValue[k]);
			}
			return radTelem;
		}
	}

	/**
	 * Calculate the telemetry and return it
	 * @return
	 
	public PayloadHERCIHousekeeping calculateHerciTelemetryPalyoad() {
		//if (isTelemetry()) {
		PayloadHERCIHousekeeping radTelem = new PayloadHERCIHousekeeping(resets, uptime, Config.satManager.getRadTelemLayout(id));
			for (int k=0; k<RadiationTelemetry.MAX_RAD_TELEM_BYTES; k++) { 
				radTelem.addNext8Bits(fieldValue[k]);
			}
			return radTelem;
		//}
		//return null;
	}
    */
	
	/**
	 * We have bytes in big endian order, so we need to add the bits in a way
	 * that makes sense when we retrieve them sequentially
	 * So we add the msb first.  Then when 12 bits pulled in a row it will make sense.
	 * Note that if we pull a subset of 8 bits, then we have to be careful of the order.
	 * @param b
	 */
	public void DATAaddNext8Bits(byte b) {
		for (int i=0; i<8; i++) {
			if ((b >> i & 1) == 1) 
				rawBits[7-i+numberBytesAdded*8] = true;
			else 
				rawBits[7-i+numberBytesAdded*8] = false;
		}
		numberBytesAdded++;	
	}

	/**
	 * Return the next n bits of the raw bit array, converted into an integer
	 * We get them sequentially, with the msb first, so they just go into the 
	 * array in order
	 * @param n
	 * @return
	*/
	protected int DATAnextbits(int n ) {
		int field = 0;
		
		boolean[] b = new boolean[n];
		for (int i=0; i < n; i++) {
			b[i] = rawBits[bitPosition+i];
			
		}
		bitPosition = bitPosition + n;
		field = FoxBitStream.binToInt(b);
		return field;
		
	}

	
	public boolean hasData() {
		copyBitsToFields();
		for (int i =0; i< MAX_PAYLOAD_RAD_SIZE; i++) {
			if (fieldValue[i] != 0) return true;
		}
		return false;
	}
	
	public String toString() {
		copyBitsToFields();
		String s = new String();
		s = s + "RADIATION EXPERIMENT DATA:\n";
		for (int i =0; i< MAX_PAYLOAD_RAD_SIZE; i++) {
			s = s + FoxDecoder.hex(fieldValue[i]) + " ";
			// Print 8 bytes in a row
			if ((i+1)%8 == 0) s = s + "\n";
		}
		return s;
	}

	public String toStringField() {
		copyBitsToFields();
		String s = new String();
		s = s + "RADIATION EXPERIMENT DATA:\n";
		for (int i=0; i < layout.fieldName.length; i++) {
			s = s + layout.fieldName[i] + ": " + fieldValue[i]+"\n";
		
		}
		return s;
	}

	
	public String toFile() {
		copyBitsToFields();
		String s = new String();
		s = s + captureDate + "," + id + "," + resets + "," + uptime + "," + type + ",";
		for (int i=0; i < fieldValue.length-1; i++) {
			s = s + FoxDecoder.dec(fieldValue[i]) + ",";
			//s = s + FoxDecoder.hex(fieldValue[i]) + ",";
		}
		// add the final field with no comma delimiter
		s = s + FoxDecoder.dec(fieldValue[fieldValue.length-1]);
		//s = s + FoxDecoder.hex(fieldValue[fieldValue.length-1]);
		return s;
	}
	
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
