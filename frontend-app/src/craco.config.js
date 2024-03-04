const TerserPlugin = require('terser-webpack-plugin');

module.exports = {
  webpack: {
    configure: (webpackConfig) => {
      webpackConfig.optimization.minimizer = [
        new TerserPlugin({
          terserOptions: {
            ecma: undefined,
            parse: {},
            compress: {},
            mangle: false,
            module: false,
            output: null,
            format: null,
            toplevel: false,
            nameCache: null,
            ie8: false,
            keep_classnames: undefined,
            keep_fnames: false,
            safari10: false,
          },
        }),
        ...webpackConfig.optimization.minimizer,
      ];

      return webpackConfig;
    },
  },
};
