# ParaMetrics: publish any parameter as a build statistic

ParaMetrics is a TeamCity plugin that lets you publish any parameter value as a build statistic. To do this, add
ParaMetrics as a build feature to your build, specifying which parameter should be published with under what statistics
key name.

You can add this build feature multiple times.

## Technical notes
If the parameter is unset when the build finishes, the statistic is not published.

## Development notes
To build, test, and package the plugin, run `mvn package` from the root directory.

To deploy the plugin, copy `target/para-metrics.zip` into the TeamCity plugin directory and restart the server.

