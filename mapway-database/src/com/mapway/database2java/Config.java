package com.mapway.database2java;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The type Config.
 *
 * @author zhangjianshe @gmail.com
 */
public class Config implements IConfigure {

    private Options mOptions;

    private CommandLine line;

    /**
     * Instantiates a new Config.
     */
    public Config() {
        mOptions = new Options();

        init();
    }

    /**
     * Parse boolean.
     *
     * @param args the args
     * @return the boolean
     * @throws ParseException the parse exception
     */
    public boolean parse(String[] args) throws ParseException {
        CommandLineParser parser = new BasicParser();
        line = parser.parse(mOptions, args);
        return true;
    }

    /**
     * Help.
     */
    public void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("DatabaseGen", mOptions);
    }

    /**
     * Inits the.
     */
    private void init() {
        Option option = OptionBuilder.withArgName("driver").hasArg().withDescription("驱动").create("driver");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("nutz").hasArg().withDescription("只生成NUTZBEAN").create("nutz");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("jdbcurl").hasArg().withDescription("JDBCURL").create("jdbcurl");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("schema").hasArg().withDescription("schema").create("schema");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("username").hasArg().withDescription("User Name").create("user");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("password").hasArg().withDescription("Password").create("pwd");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("package").hasArg().withDescription("Package ").create("package");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("srcpath").hasArg().withDescription("out path").create("path");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("maxconnections").hasArg().withDescription("connection count")
                .create("maxconnections");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("useDocument").hasArg().withDescription("is use mapay document annotation")
                .create("useDocument");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("useGwt").hasArg().withDescription("is use Gwt related interface")
                .create("useGwt");
        mOptions.addOption(option);

        option = OptionBuilder.withArgName("useFieldStyle").hasArg().withDescription("fieldStyle LOWER_CASE UPPER_CASE CAMEL")
                .create("useFieldStyle");
        mOptions.addOption(option);
    }

    /**
     * ��ݿ������.
     *
     * @return the driver
     */
    @Override
    public String getDriver() {
        return line.getOptionValue("driver", "com.mysql.jdbc.Driver");
    }

    /**
     * ��ݿ�����URL.
     *
     * @return the jdbcurl
     */
    @Override
    public String getJDBCURL() {
        return line.getOptionValue("jdbcurl", "");
    }

    /**
     * Gets the max connections.
     *
     * @return the max connections
     */
    @Override
    public int getMaxConnections() {
        return Integer.valueOf(line.getOptionValue("maxconnections", "20"));
    }

    /**
     * Gets the schema.
     *
     * @return the schema
     */
    @Override
    public String getSchema() {
        return line.getOptionValue("schema", "schema");
    }

    /**
     * Gets the user.
     *
     * @return the user
     */
    @Override
    public String getUser() {
        return line.getOptionValue("user", "user");
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return line.getOptionValue("pwd", "pwd");
    }

    /**
     * Gets the package.
     *
     * @return the package
     */
    @Override
    public String getPackage() {
        return line.getOptionValue("package", "com.mapway.noapp");
    }

    /**
     * Gets the path.
     *
     * @return the path
     */
    @Override
    public String getPath() {
        return line.getOptionValue("path", "");
    }

    /**
     * Gets the nutz.
     *
     * @return the nutz
     */
    @Override
    public String getUseFieldStyle() {
        return line.getOptionValue("useFieldStyle", "CAMEL");
    }

    @Override
    public String getUseGwt() {
        return line.getOptionValue("useGwt", "0");
    }

    @Override
    public String getUseDocument() {
        return line.getOptionValue("useDocument", "1");
    }

    @Override
    public String getMapper() {
        return line.getOptionValue("mapper", "");
    }

    @Override
    public String getUseNutz() {
        return line.getOptionValue("useNutz", "1");
    }

    @Override
    public String getUseFieldIndex() {
        return line.getOptionValue("useFieldIndex", "0");
    }
}
