package me.creighton.scramble;

import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

@Command(name = "encode-decode",
    subcommands = {
        Encode.class,
        Decode.class
    }
)
public class ScrambleIt implements Callable<Integer> {

    @Override
    public Integer call () {
        // Default action is decode
        System.out.println("scrambling...");
        return 0;
    }

}
