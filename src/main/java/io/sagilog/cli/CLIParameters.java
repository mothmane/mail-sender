package io.sagilog.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CLIParameters {
    public static Options configParameters(final Options firstOptions) {

        final Option fromOption = Option.builder("f")
                .longOpt("from") //
                .desc("the sender of the email")
                .hasArg(true)
                .argName("from")
                .required(true)
                .build();

        final Option subjectOption = Option.builder("s")
                .longOpt("subject")
                .desc("subject of the email")
                .hasArg(true)
                .argName("subject")
                .required(true)
                .build();

        final Option templateFileOption = Option.builder("t")
                .longOpt("template")
                .desc("template of the email")
                .hasArg(true)
                .required(true)
                .build();

        final Option clientsFileOption = Option.builder("c")
                .longOpt("client")
                .desc("file containing clients data")
                .hasArg(true)
                .required(true)
                .build();

        final Options options = new Options();
        // First Options
        for (final Option fo : firstOptions.getOptions()) {
            options.addOption(fo);
        }

        options.addOption(fromOption);
        options.addOption(subjectOption);
        options.addOption(templateFileOption);
        options.addOption(clientsFileOption);

        return options;
    }

    public static Options configFirstParameters() {

        final Option helpFileOption = Option.builder("h")
                .longOpt("help")
                .desc("show help message")
                .build();

        final Options firstOptions = new Options();

        firstOptions.addOption(helpFileOption);

        return firstOptions;
    }
}
