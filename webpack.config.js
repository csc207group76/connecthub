const path = require("path")
const MiniCssExtractPlugin = require("mini-css-extract-plugin")
const webpack = require("webpack")

const presets = [
  ["@babel/preset-env", { useBuiltIns: "usage", corejs: 3 }],
  ["@babel/preset-react", { runtime: "automatic" }],
]

module.exports = {
  mode: "development",
  entry: {
    "js/styles/app": "./styles/style.css",
    "js/sign_up/app": "./js/sign_up/main.js",
    "js/login/app": "/js/login/main.js",
    "js/home_page/app": "/js/home_page/main.js",
    "js/create_post/app": "/js/create_post/main.js",
    "js/post/app": "/js/post/main.js",
  },
  output: {
    path: path.resolve(__dirname, path.resolve(__dirname, 'src/main/resources/static')),
    filename: "[name].js",
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        use: {
          loader: "babel-loader",
          options: { presets },
        },
        exclude: /node_modules/,
      },
      {
        test: /\.jsx$/,
        use: {
          loader: "babel-loader",
          options: { presets },
        },
        exclude: /node_modules/,
      },
      {
        test: /\.svg$/,
        type: 'asset/resource',
        generator: {
          filename: 'assets/[name][hash][ext][query]',
        },
      },
      {
        test: /\.css$/i,
        use: [MiniCssExtractPlugin.loader, "css-loader"],
      },
      {
        test: /\.(png|jpe?g|gif)$/i,
        use: [
          {
            loader: 'file-loader',
          },
        ],
      },
    ],
  },
  plugins: [
    new MiniCssExtractPlugin({
      filename: "[name].css",
      chunkFilename: "[id].css",
    }),
  ],
}
