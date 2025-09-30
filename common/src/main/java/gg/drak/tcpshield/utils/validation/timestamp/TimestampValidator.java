package gg.drak.tcpshield.utils.validation.timestamp;

import gg.drak.tcpshield.TCPShieldMod;

/**
 * Base for timestamp validators
 */
public abstract class TimestampValidator {

	/**
	 * Creates an empty validator that always returns true
	 * @return An empty validator always returning true
	 */
	public static TimestampValidator createEmpty() {
		return new TimestampValidator() {
			@Override
			public boolean validate(long timestamp) {
				return true;
			}
		};
	}

	/**
	 * Creates a system validator that uses default validation
	 * @return A system validator
	 */
	public static TimestampValidator createDefault() {
		return new TimestampValidator() {
			// Uses default methods
		};
	}

	public TimestampValidator() {
	}


	/**
	 * Validates given timestamp to see if it's within a reasonable
	 * time range
	 * @param timestamp The timestamp
	 * @return Boolean stating if its valid
	 */
	public boolean validate(long timestamp) {
		return Math.abs(getUnixTime() - timestamp) <= TCPShieldMod.INSTANCE.getConfigProvider().getMaxTimestampDifference();
	}


	/**
	 * Returns the validator's current Unix time
	 * @return The current time in Unix format
	 * @default Returns the system time in Unix format
	 */
	public long getUnixTime() {
		return System.currentTimeMillis() / 1000;
	}
}