package telemetry.conversion;

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
 *
 * Look up table for the temperature sensors on the battery board
 *
 */
@Deprecated
public class LookUpTableBatteryTemp extends ConversionLookUpTable {
	@Deprecated
public LookUpTableBatteryTemp() {
	super("LookUpTableBatteryTemp", null);
	table.put(608,-55.0);
	table.put(612,-54.0);
	table.put(616,-53.0);
	table.put(620,-52.0);
	table.put(624,-51.0);
	table.put(629,-50.0);
	table.put(634,-49.0);
	table.put(639,-48.0);
	table.put(645,-47.0);
	table.put(651,-46.0);
	table.put(657,-45.0);
	table.put(663,-44.0);
	table.put(670,-43.0);
	table.put(677,-42.0);
	table.put(685,-41.0);
	table.put(693,-40.0);
	table.put(701,-39.0);
	table.put(710,-38.0);
	table.put(719,-37.0);
	table.put(729,-36.0);
	table.put(739,-35.0);
	table.put(750,-34.0);
	table.put(761,-33.0);
	table.put(772,-32.0);
	table.put(785,-31.0);
	table.put(797,-30.0);
	table.put(810,-29.0);
	table.put(824,-28.0);
	table.put(839,-27.0);
	table.put(854,-26.0);
	table.put(869,-25.0);
	table.put(885,-24.0);
	table.put(902,-23.0);
	table.put(920,-22.0);
	table.put(938,-21.0);
	table.put(957,-20.0);
	table.put(976,-19.0);
	table.put(996,-18.0);
	table.put(1017,-17.0);
	table.put(1039,-16.0);
	table.put(1061,-15.0);
	table.put(1085,-14.0);
	table.put(1108,-13.0);
	table.put(1133,-12.0);
	table.put(1158,-11.0);
	table.put(1184,-10.0);
	table.put(1211,-9.0);
	table.put(1238,-8.0);
	table.put(1267,-7.0);
	table.put(1296,-6.0);
	table.put(1325,-5.0);
	table.put(1356,-4.0);
	table.put(1387,-3.0);
	table.put(1418,-2.0);
	table.put(1451,-1.0);
	table.put(1484,0.0);
	table.put(1518,1.0);
	table.put(1552,2.0);
	table.put(1587,3.0);
	table.put(1622,4.0);
	table.put(1659,5.0);
	table.put(1695,6.0);
	table.put(1732,7.0);
	table.put(1770,8.0);
	table.put(1808,9.0);
	table.put(1846,10.0);
	table.put(1885,11.0);
	table.put(1925,12.0);
	table.put(1964,13.0);
	table.put(2004,14.0);
	table.put(2044,15.0);
	table.put(2085,16.0);
	table.put(2125,17.0);
	table.put(2166,18.0);
	table.put(2207,19.0);
	table.put(2248,20.0);
	table.put(2289,21.0);
	table.put(2330,22.0);
	table.put(2371,23.0);
	table.put(2412,24.0);
	table.put(2453,25.0);
	table.put(2494,26.0);
	table.put(2534,27.0);
	table.put(2575,28.0);
	table.put(2615,29.0);
	table.put(2655,30.0);
	table.put(2695,31.0);
	table.put(2735,32.0);
	table.put(2774,33.0);
	table.put(2813,34.0);
	table.put(2851,35.0);
	table.put(2890,36.0);
	table.put(2927,37.0);
	table.put(2965,38.0);
	table.put(3002,39.0);
	table.put(3038,40.0);
	table.put(3074,41.0);
	table.put(3110,42.0);
	table.put(3145,43.0);
	table.put(3179,44.0);
	table.put(3213,45.0);
	table.put(3247,46.0);
	table.put(3280,47.0);
	table.put(3312,48.0);
	table.put(3344,49.0);
	table.put(3375,50.0);
	table.put(3406,51.0);
	table.put(3436,52.0);
	table.put(3466,53.0);
	table.put(3495,54.0);
	table.put(3523,55.0);
	table.put(3551,56.0);
	table.put(3579,57.0);
	table.put(3605,58.0);
	table.put(3632,59.0);
	table.put(3657,60.0);
	table.put(3682,61.0);
	table.put(3707,62.0);
	table.put(3731,63.0);
	table.put(3755,64.0);
	table.put(3778,65.0);
	table.put(3800,66.0);
	table.put(3822,67.0);
	table.put(3843,68.0);
	table.put(3864,69.0);
	table.put(3885,70.0);
	table.put(3905,71.0);
	table.put(3924,72.0);
	table.put(3943,73.0);
	table.put(3962,74.0);
	table.put(3980,75.0);
	table.put(3997,76.0);
	table.put(4015,77.0);
	table.put(4031,78.0);
	table.put(4048,79.0);
	table.put(4064,80.0);
	table.put(4079,81.0);
	table.put(4094,82.0);
	table.put(4109,83.0);
	table.put(4124,84.0);
	table.put(4138,85.0);
	table.put(4151,86.0);
	table.put(4165,87.0);
	table.put(4177,88.0);
	table.put(4190,89.0);
	table.put(4202,90.0);
	table.put(4214,91.0);
	table.put(4226,92.0);
	table.put(4237,93.0);
	table.put(4248,94.0);
	table.put(4259,95.0);
	table.put(4270,96.0);
	table.put(4280,97.0);
	table.put(4290,98.0);
	table.put(4300,99.0);
	table.put(4309,100.0);
	table.put(4318,101.0);
	table.put(4327,102.0);
	table.put(4336,103.0);
	table.put(4344,104.0);
	table.put(4353,105.0);
	table.put(4361,106.0);
	table.put(4369,107.0);
	table.put(4376,108.0);
	table.put(4384,109.0);
	table.put(4391,110.0);
	table.put(4398,111.0);
	table.put(4405,112.0);
	table.put(4412,113.0);
	table.put(4418,114.0);
	table.put(4424,115.0);
	table.put(4431,116.0);
	table.put(4437,117.0);
	table.put(4442,118.0);
	table.put(4448,119.0);
	table.put(4454,120.0);
	table.put(4459,121.0);
	table.put(4464,122.0);
	table.put(4470,123.0);
	table.put(4475,124.0);
	table.put(4479,125.0);
	table.put(4484,126.0);
	table.put(4489,127.0);
	table.put(4493,128.0);
	table.put(4498,129.0);
	table.put(4502,130.0);
	table.put(4506,131.0);
	table.put(4510,132.0);
	table.put(4514,133.0);
	table.put(4518,134.0);
	table.put(4522,135.0);
	table.put(4526,136.0);
	table.put(4529,137.0);
	table.put(4533,138.0);
	table.put(4536,139.0);
	table.put(4540,140.0);
	table.put(4543,141.0);
	table.put(4546,142.0);
	table.put(4549,143.0);
	table.put(4552,144.0);
	table.put(4555,145.0);
	table.put(4558,146.0);
	table.put(4561,147.0);
	table.put(4564,148.0);
	table.put(4566,149.0);
	table.put(4569,150.0);

}

}