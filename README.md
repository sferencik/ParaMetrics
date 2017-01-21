# ParaMetrics: publish any parameter as a build statistic [![Build Status](https://travis-ci.org/sferencik/ParaMetrics.svg?branch=master)](https://travis-ci.org/sferencik/ParaMetrics)

ParaMetrics is a TeamCity plugin that lets you publish any parameter value as a build statistic. To do this, add
ParaMetrics as a build feature to your build, specifying which parameter should be published under what statistics key
name.

You can add this build feature multiple times.

## Technical notes
If the parameter is unset when the build finishes, the statistic is not published.

## Development notes
To build, test, and package the plugin, run `mvn package` from the root directory.

To deploy the plugin, copy `para-metrics.zip` into the TeamCity plugin directory and restart the server. If you've built locally, get the zip file from `target/para-metrics.zip`; otherwise grab the [latest released version](https://github.com/sferencik/ParaMetrics/releases).

