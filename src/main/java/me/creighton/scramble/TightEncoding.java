package me.creighton.scramble;

import me.creighton.encodedid.IBigIntegerEncoder;
import me.creighton.encodedid.IEncodedId;
import me.creighton.encodedid.ILongEncoder;
import me.creighton.encodedid.IUuidEncoder;

import static me.creighton.encodedid.IEncodedId.getTightlyEncodedIdBuilder;

/**
 * This class wraps the TightlyEncodedIdBuilder to simplify the main code.
 */
public class TightEncoding {

    public static ILongEncoder getLongTightEncoder() {
        ILongEncoder longEncoder = ILongEncoder.build(getTightlyEncodedIdBuilder());

        return longEncoder;
    }

    public static IBigIntegerEncoder getBigIntegerTightEncoder () {
        IBigIntegerEncoder bigIntegerEncoder = IBigIntegerEncoder.build((getTightlyEncodedIdBuilder()));

        return bigIntegerEncoder;
    }

    public static IUuidEncoder getUuidTightEncoder () {
        IUuidEncoder uuidEncoder = IUuidEncoder.build((getTightlyEncodedIdBuilder()));

        return uuidEncoder;
    }


}
