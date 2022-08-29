package me.creighton.scramble;

import me.creighton.encodedid.*;

/**
 * This class encapsulates the alphabet needed to match what is
 * used in FamilySearch JEncoding. While it's not exactly secret,
 * we don't advertise it. So that's why it's in scramble and not
 * inherently part of brevis. Also, you invoke this encoding by
 * the 'hidden' option -j.
 */
public class JEncoding {
    private static final String ALPHABET = "M9S3Q7W4HCZ8D6XFNJVK2LGP5RTYB1";

    public static ILongEncoder getLongJEncoder() {
        ILongEncoder longEncoder = ILongEncoder.build(
                IEncodedId.getEncodedIdBuilder(ALPHABET, "123456789" + "BCDFGHJKLMNPQRSTVWXYZ")
                        .padWidth(8)
                        .separator(true)

        );

        return longEncoder;
    }

    public static IBigIntegerEncoder getBigIntegerJEncoder() {
        IBigIntegerEncoder bigIntegerEncoder = IBigIntegerEncoder.build(
                IEncodedId.getEncodedIdBuilder(ALPHABET, "123456789" + "BCDFGHJKLMNPQRSTVWXYZ")
                        .padWidth(8)
                        .separator(true)

        );

        return bigIntegerEncoder;
    }

    public static IUuidEncoder getUuidJEncoder() {
        IUuidEncoder uuidEncoder = IUuidEncoder.build(
                IEncodedId.getEncodedIdBuilder(ALPHABET, "123456789" + "BCDFGHJKLMNPQRSTVWXYZ")
                        .padWidth(8)
                        .separator(true)

        );

        return uuidEncoder;
    }

}
