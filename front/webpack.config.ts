import * as path from 'path'
import * as webpack from 'webpack'

const config: webpack.Configuration = {
  mode: 'production',
  entry: {
    script: './src/entry.tsx',
  },
  output: {
    path: path.resolve(__dirname, '../src/main/resources/static/js'),
    filename: '[name].js',
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js'],
  },
  module: {
    rules: [
      {
        test: /\.tsx?$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
        },
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      },
    ],
  },
  optimization: {
    usedExports: true,
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        common: {
          name: 'common',
          test: /[\\/]node_modules[\\/]/,
        },
      },
    },
  },
  devtool: 'inline-source-map',
}

export default config
