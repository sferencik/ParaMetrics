package sferencik.teamcity.parametrics;

import jetbrains.buildServer.serverSide.BuildFeature;
import jetbrains.buildServer.serverSide.InvalidProperty;
import jetbrains.buildServer.serverSide.PropertiesProcessor;
import jetbrains.buildServer.util.StringUtil;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ParaMetricsBuildFeature extends BuildFeature {
    private final PluginDescriptor descriptor;

    public ParaMetricsBuildFeature(@NotNull final PluginDescriptor descriptor) {
        this.descriptor = descriptor;
    }

    @NotNull
    @Override
    public String getType() {
        return Names.FEATURE_NAME;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Convert params to statistics (ParaMetrics)";
    }

    @Nullable
    @Override
    public String getEditParametersUrl() {
        return descriptor.getPluginResourcesPath("paraMetricsSettings.jsp");
    }

    @Override
    public boolean isMultipleFeaturesPerBuildTypeAllowed() {
        return true;
    }

    @NotNull
    @Override
    public String describeParameters(@NotNull Map<String, String> params) {
        final Names names = new Names();
        final String parameterName = params.get(names.getParameterNameParameterName());
        final String statisticName = params.get(names.getStatisticNameParameterName());
        if (StringUtil.isEmpty(parameterName) || StringUtil.isEmpty(statisticName)) {
            // should not happen, thanks to getParametersProcessor()
            return "ERROR: parameter or statistic name unspecified";
        }
        else {
            return "Publish the value of '" +
                    parameterName +
                    "' as the '" +
                    statisticName +
                    "' statistic value";
        }
    }

    @Nullable
    @Override
    public PropertiesProcessor getParametersProcessor() {
        return new PropertiesProcessor() {
            @Override
            public Collection<InvalidProperty> process(Map<String, String> params) {
                final Names names = new Names();
                List<InvalidProperty> issues = new ArrayList<InvalidProperty>();

                final String parameterNameParameterName = names.getParameterNameParameterName();
                final String parameterName = params.get(parameterNameParameterName);
                if (StringUtil.isEmpty(parameterName)) {
                    issues.add(new InvalidProperty(parameterNameParameterName, "Please specify the parameter name"));
                }

                final String statisticNameParameterName = names.getStatisticNameParameterName();
                final String statisticName = params.get(statisticNameParameterName);
                if (StringUtil.isEmpty(statisticName)) {
                    issues.add(new InvalidProperty(statisticNameParameterName, "Please specify the statistic name"));
                }

                return issues;
            }
        };
    }
}
