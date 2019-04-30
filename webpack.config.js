const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
    entry  : './Default/src/main/webapp/WEB-INF/react/client.js',
    output : {
        path : path.join(__dirname,'/Default/src/main/webapp/WEB-INF/react'),
        filename : 'client.min.js'
    },
    module : {
        rules : [
            {
                test : /\.js$/ ,
                exclude : /node_modules/,
                use : {
                    loader : 'babel-loader',
                    options: {
                        presets: ['@babel/env','@babel/react'],
                        plugins : [
                        
                            "@babel/proposal-class-properties"
                          ]
                      }
                }
            },{
                test: /\.scss$/,
                exclude: /node_modules/,
                loaders: ["style-loader", "css-loader", "postcss-loader", "sass-loader"]
            }, {
                test: /\.css$/,
                loader: ["style-loader", "css-loader", "postcss-loader"]
            }, {
                test: /\.(jpe?g|gif|png|svg)$/i,
                loader: 'url-loader?limit=10000'
            }
        ]
    },
    node: {
        fs: "empty"
      }
} 