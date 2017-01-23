package sferencik.teamcity.parametrics;

import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.BuildServerListener;
import jetbrains.buildServer.serverSide.SBuildFeatureDescriptor;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.serverSide.statistics.build.BuildDataStorage;
import jetbrains.buildServer.util.EventDispatcher;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class BuildStatusListener {
    public BuildStatusListener(@NotNull final EventDispatcher<BuildServerListener> listener,
                               @NotNull final BuildDataStorage dataStorage)
    {
        listener.addListener(new BuildServerAdapter()
        {
            @Override
            public void buildFinished(@NotNull SRunningBuild sBuild)
            {
                Names names = new Names();
                for (SBuildFeatureDescriptor feature : sBuild.getBuildFeaturesOfType(Names.FEATURE_NAME)) {
                    final String parameterName = feature.getParameters().get(names.getParameterNameParameterName());
                    if (StringUtil.isEmpty(parameterName)) {
                        // should not happen, thanks to getParametersProcessor()
                        Logger.error("Parameter name unset; cannot publish the statistic");
                        continue;
                    }

                    final String statisticName = feature.getParameters().get(names.getStatisticNameParameterName());
                    if (StringUtil.isEmpty(statisticName)) {
                        // should not happen, thanks to getParametersProcessor()
                        Logger.error("Statistic name unset; cannot publish the statistic");
                        continue;
                    }

                    final String value = sBuild.getParametersProvider().get(parameterName);
                    if (StringUtil.isEmpty(value)) {
                        Logger.error("Parameter " + parameterName + " is unset; cannot publish the statistic");
                        continue;
                    }

                    final long buildId = sBuild.getBuildId();
                    Logger.info(String.format("Publishing %s = %s for build %d", statisticName, value, buildId));
                    dataStorage.publishValue(statisticName, buildId, new BigDecimal(value));
                }
            }
        });
    }
}

