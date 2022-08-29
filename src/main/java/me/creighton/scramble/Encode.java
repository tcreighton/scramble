package me.creighton.scramble;

import me.creighton.encodedid.IBigIntegerEncoder;
import me.creighton.encodedid.IEncodedId;
import me.creighton.encodedid.ILongEncoder;
import me.creighton.encodedid.IUuidEncoder;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.Callable;

import static java.util.UUID.fromString;
import static me.creighton.scramble.ValueType.*;

@Command(name = "encode")
public class Encode implements Callable<Integer> {
    @Parameters(description = "ID to encode")
    private String id;
    @Option(names = {"-vt", "--valuetype"},
            description = "type indicator for object: L=Long, B=BigInteger, U=uuid",
            defaultValue = "L")
    private String vt;
    @Option(names = {"-j", "--jEncoding"},
            description = "Perform encode/decode using the FamilySearch JEncoding Alphabet.")
    private boolean jEncoding = false;
    @Option(names = {"-t", "--tight"},
            description = "Perform tight encoding.")
    private boolean tightEncoding = false;

    private ValueType valueType;
    private String encodedValue = "";

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
        System.out.printf("Encoding %s as type %s.\n", id, valueType);

        // Do the encoding
        switch (this.valueType) {
            case LONG:
                System.out.printf("JEncoding: %s\n", this.jEncoding);
                ILongEncoder longEncoder =
                        this.jEncoding
                            ? JEncoding.getLongJEncoder()
                            : this.tightEncoding
                                ? TightEncoding.getLongTightEncoder()
                                : ILongEncoder.build(IEncodedId.getEncodedIdBuilder());
                long longId = Long.parseLong(this.id);

                this.encodedValue = longEncoder.encodeId(longId);
                break;

            case BIGINTEGER:
                IBigIntegerEncoder bigIntegerEncoder =
                        this.jEncoding
                            ? JEncoding.getBigIntegerJEncoder()
                            :  this.tightEncoding
                                ? TightEncoding.getBigIntegerTightEncoder()
                                : IBigIntegerEncoder.build(IEncodedId.getEncodedIdBuilder());
                BigInteger bigInteger = new BigInteger(id, 10);
                this.encodedValue = bigIntegerEncoder.encodeId(bigInteger);
                break;

            case UUID:
                IUuidEncoder uuidEncoder =
                        this.jEncoding
                            ? JEncoding.getUuidJEncoder()
                            :  this.tightEncoding
                                ? TightEncoding.getUuidTightEncoder()
                                : IUuidEncoder.build(IEncodedId.getEncodedIdBuilder());
                UUID uuid = fromString(this.id);

                this.encodedValue = uuidEncoder.encodeId(uuid);
                break;
        }

        System.out.printf("%s %s length %d encodes as %s length %d\n",
                            this.vt, this.id, this.id.length(), this.encodedValue, this.encodedValue.length());


        return 0;
    }
}
