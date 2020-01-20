package telemetry.FoxBPSK;

import common.Config;
import common.FoxSpacecraft;
import common.Spacecraft;
import decoder.FoxDecoder;
import telemetry.BitArrayLayout;
import telemetry.Header;
import telemetry.TelemFormat;

public class FoxBPSKHeader extends Header {
	// Extended Mode Bits that are only in FoxId 6 and later
	int safeMode;
	int healthMode;
	int scienceMode;
	int cameraMode;
	int minorVersion;
	TelemFormat telemFormat;
	public static final String ID_FIELD = "satelliteId";
	public static final String TYPE_FIELD = "type";
	public static final String RESET_FIELD = "resetCnt";
	public static final String UPTIME_FIELD = "uptime";
	public static final String PROTOCOL_VERSION_FIELD = "protocolVersion";
	public static final String MODES_FIELD = "modes";

	public FoxBPSKHeader(BitArrayLayout layout, TelemFormat telemFormat) {
		super(TYPE_EXTENDED_HEADER, layout);
		this.telemFormat = telemFormat;
		MAX_BYTES = telemFormat.getInt(TelemFormat.HEADER_LENGTH);
		rawBits = new boolean[MAX_BYTES*8];
	}
	
	public int getType() { return type; }
	
	/**
	 * Take the bits from the raw bit array and copy them into the fields
	 */
	public void copyBitsToFields() {
		if (this.layout.fieldName != null) {
			// We have a layout, so use that.  GOLF-T and later
			super.copyBitsToFields();
			id = getRawValue(ID_FIELD);
			resets = getRawValue(RESET_FIELD);
			uptime = getRawValue(UPTIME_FIELD);
			type = getRawValue(TYPE_FIELD);
			minorVersion = getRawValue(PROTOCOL_VERSION_FIELD);
			int modes = getRawValue(MODES_FIELD);
		} else {
			super.copyBitsToFields();
			type = nextbits(4);
			if (id == 0) // then take the foxId from the next 8 bits
				id = nextbits(8);
			if (id >= Spacecraft.FIRST_FOXID_WITH_MODE_IN_HEADER) { // Post Fox-1E BPSK has mode in header
				safeMode = nextbits(1);
				healthMode = nextbits(1);
				scienceMode = nextbits(1);
				cameraMode = nextbits(1);
				minorVersion = nextbits(4);
			}
			setMode();
		}
	}

	public void setMode() {
		newMode = FoxSpacecraft.NO_MODE;
		if (id >= Spacecraft.FIRST_FOXID_WITH_MODE_IN_HEADER) {
			if (safeMode != 0 ) newMode = FoxSpacecraft.SAFE_MODE;
			if (healthMode != 0 ) newMode = FoxSpacecraft.HEALTH_MODE;
			if (scienceMode != 0 ) newMode = FoxSpacecraft.SCIENCE_MODE;
			if (cameraMode != 0 ) newMode = FoxSpacecraft.CAMERA_MODE;
		}
	
	}

	public boolean isValid() {
		copyBitsToFields();
		if (Config.satManager.validFoxId(id))
			return true;
		return false;
	}
	
	// TODO: This needs to be the 1E frame types
	public boolean isValidType(int t) {
	
		assert(false);
		return false;
			
	}
		
	public String toString() {
		copyBitsToFields();
		String s = new String();
		s = s	+ "ID: " + FoxDecoder.dec(id) 
				+ " RESET COUNT: " + FoxDecoder.dec(resets)
				+ " UPTIME: " + FoxDecoder.dec(uptime)
				+ " TYPE: " + FoxDecoder.dec(type);
		
		if (id >= Spacecraft.FIRST_FOXID_WITH_MODE_IN_HEADER)
			s = s + " - MODE: " + newMode;
		return s;
	}
}
