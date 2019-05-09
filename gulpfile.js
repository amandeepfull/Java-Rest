const { src, dest, series, watch } = require('gulp');


const paths = {
  root:         'Default/src/main/webapp/',
  buildRoot:    'Default/build/exploded-Default/'

}


//running webpack

const webpack = require('webpack');
const webpackStream = require('webpack-stream');
const webpackConfig = require('./webpack.config.js');

webpack_mode = ((done) => {
 src(paths.root +"react/client.js")
    .pipe(webpackStream(webpackConfig), webpack)
    .pipe(dest(paths.root + "js"));
});

// Enables watch mode on CSS, JSP & JS
watch_mode = ((done) => {

 watch(paths.root + 'css/*.css', css);
  watch(paths.root + 'js/*.js', js);
  watch(paths.root + 'pages/jsp/*.jsp', jsp);
  watch(paths.root + 'pages/html/*.html', html);
  done();
});

// Exports JS changes into build folder
js = ((done) => {
  src(paths.root + 'js/*.js')
    .pipe(dest(paths.buildRoot + 'js'));
  done();
});

html = ((done) => {
  src(paths.root + 'pages/html/*.html')
    .pipe(dest(paths.buildRoot + 'pages/html'));
  done();
});



css = ((done) => {

  src(paths.root + 'css/*.css')
    .pipe(dest(paths.buildRoot + 'css'));
  done();
});

// Exports JSP changes into build folder
jsp = (() => {
  return src(paths.root + 'pages/jsp/*.jsp')
    .pipe(dest(paths.buildRoot + 'pages/jsp'))
});

exports.default = series(html,css, js, jsp, watch_mode, webpack_mode);

