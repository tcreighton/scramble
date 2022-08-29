package me.creighton.scramble;

import me.creighton.encodedid.IBigIntegerEncoder;
import me.creighton.encodedid.IEncodedId;
import me.creighton.encodedid.ILongEncoder;
import me.creighton.encodedid.IUuidEncoder;
import picocli.CommandLine;

import java.math.BigInteger;
import java.util.Locale;
import java.util.concurrent.Callable;

import static me.creighton.scramble.ValueType.*;

@CommandLine.Command(name = "decode")
public class Decode implements Callable<Integer> {
    @CommandLine.Parameters(description = "Encoded ID to decode")
    private String encodedId;

    @CommandLine.Option(names = {"-vt", "--valuetype"},
            description = "type indicator for object: L=Long, B=BigInteger, U=uuid",
            defaultValue = "L")
    private String vt;

    @CommandLine.Option(names = {"-j", "--JEncoding"},
            description = "Perform encode/decode using the FamilySearch JEncoding Alphabet.")
    private boolean jEncoding = false;

    @CommandLine.Option(names = {"-t", "--tight"},
            description = "Perform tight encoding.")
    private boolean tightEncoding = false;

    private ValueType valueType;
    private String decodedValue = "";

    @Override
    public Integer call () {

        // Set the encoding type
        switch (this.vt.toUpperCase()) {
            case "L":
            default:    // assume it's an integer
                this.valueType = LONG;
                break;
            case "B":
                this.valueType = BIGINTEGER;
                break;
            case "U":
                this.valueType = UUID;
                break;
        }
        System.out.printf("Decoding %s as type %s.\n", encodedId, valueType);

        // Do the decoding
        switch (this.valueType) {
            case LONG:

                if (this.jEncoding) {
                    this.encodedId = this.encodedId.toUpperCase(Locale.ROOT);
                }

                ILongEncoder longEncoder =
                        this.jEncoding
                                ? JEncoding.getLongJEncoder()
                                : this.tightEncoding
                                    ? TightEncoding.getLongTightEncoder()
                                    : ILongEncoder.build(IEncodedId.getEncodedIdBuilder());

                this.decodedValue = String.valueOf(longEncoder.decodeId(this.encodedId));
                break;

            case BIGINTEGER:

                if (this.jEncoding) {
                    this.encodedId = this.encodedId.toUpperCase(Locale.ROOT);
                }

                IBigIntegerEncoder bigIntegerEncoder =
                        this.jEncoding
                                ? JEncoding.getBigIntegerJEncoder()
                                :  this.tightEncoding
                                    ? TightEncoding.getBigIntegerTightEncoder()
                                    : IBigIntegerEncoder.build(IEncodedId.getEncodedIdBuilder());

                this.decodedValue = String.valueOf(bigIntegerEncoder.decodeId(this.encodedId));

                break;

            case UUID:

                if (this.jEncoding) {
                    this.encodedId = this.encodedId.toUpperCase(Locale.ROOT);
                }

                IUuidEncoder uuidEncoder =
                        this.jEncoding
                                ? JEncoding.getUuidJEncoder()
                                :  this.tightEncoding
                                    ? TightEncoding.getUuidTightEncoder()
                                    : IUuidEncoder.build(IEncodedId.getEncodedIdBuilder());

                this.decodedValue = String.valueOf(uuidEncoder.decodeId(this.encodedId));
                break;
        }

        System.out.printf("%s %s length %d decodes as %s length %d\n",
                this.vt, this.encodedId, this.encodedId.length(), this.decodedValue, this.decodedValue.length());


        return 0;
    }

}
/* Some type 4 UUIDs with default encoding
/*         UUID                               Encoded                 Tightly
e599091d-54b7-4384-8561-95bad0dc5340  DSPKWQWPMRTHBYDCD5Y74L232NN 91Ug1cVuGoy0a6&iVcBif
c8dc385a-f4af-48b5-b264-f1442e09ed56
23f35858-819a-4635-a9bc-f2ba200bb6dc
d441d980-4a36-4be8-acbf-38e73f40d68c
ef7df576-6807-4bde-b186-1af0582311b3
3532031c-7bde-4686-b0a5-8f7b792fabf9
 */