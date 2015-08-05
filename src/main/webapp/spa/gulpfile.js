// Node modules
var fs = require('fs'),
    vm = require('vm'),
    merge = require('deeply'),
    chalk = require('chalk'),
    es = require('event-stream'),
    streamqueue = require('streamqueue');

// Gulp and plugins
var gulp = require('gulp'),
    rjs = require('gulp-requirejs-bundler'),
    concat = require('gulp-concat'),
    clean = require('gulp-clean'),
    replace = require('gulp-replace'),
    uglify = require('gulp-uglify'),
    htmlreplace = require('gulp-html-replace'),
    less = require('gulp-less'),
    uglifycss = require('gulp-uglifycss'),
    server = require('gulp-server-livereload');

// Config
var requireJsRuntimeConfig = vm.runInNewContext(fs.readFileSync('src/app/require.config.js') + '; require;');
requireJsOptimizerConfig = merge(requireJsRuntimeConfig, {
    out: 'scripts.js',
    baseUrl: './src',
    name: 'app/startup',
    paths: {
        requireLib: 'bower_modules/requirejs/require'
    },
    include: [
        'requireLib',
        'components/login/login',
        'components/nav-bar/nav-bar',
        'components/baiye-header/baiye-header',
        'components/baiye-footer/baiye-footer',
        'components/home-page/home',
        'text!components/about-page/about.html'
    ],
    insertRequire: ['app/startup'],
    bundles: {
        // If you want parts of the site to load on demand, remove them from the 'include' list
        // above, and group them into bundles here.
        // 'bundle-name': [ 'some/module', 'another/module' ],
        // 'another-bundle-name': [ 'yet-another-module' ]
        //'about-page': ['text!components/about-page/about.html']

    }
});

// watch less change.
gulp.task('less', function() {
    return gulp.src('./src/css/styles.less')
        .pipe(less())
        .pipe(gulp.dest('./src/css/'));
});


// Discovers all AMD dependencies, concatenates together all required .js files, minifies them
gulp.task('js', function() {
    return rjs(requireJsOptimizerConfig)
        .pipe(uglify({
            // preserveComments: 'some'
        }))
        .pipe(gulp.dest('./dist/'));
});

// Concatenates CSS files, rewrites relative paths to Bootstrap fonts, copies Bootstrap fonts
gulp.task('css', function() {

    var bootstrapCss = gulp.src('src/bower_modules/components-bootstrap/css/bootstrap.css')
                .pipe(replace(/url\(('|")?\.\.\/fonts\//g, 'url($1fonts/')),
        dataTableCss = gulp.src('src/bower_modules/DataTables/media/css/jquery.dataTables.css')
                .pipe(replace(/url\(('|")?\.\.\/images\//g, 'url($1imgs/')),
        jqueryConfirmCss = gulp.src('src/bower_modules/jquery-confirm2/dist/jquery-confirm.css'),
        appCss = gulp.src('src/css/*.css')
                .pipe(replace(/url\(('|")?\.\.\/imgs\//g, 'url($1imgs/')),
        combinedCss = streamqueue({
            objectMode: true
        }, bootstrapCss, dataTableCss, jqueryConfirmCss, appCss).pipe(concat('css.css')).pipe(uglifycss({
            'max-line-len': 80,
            'cute-comments': true
        })),
        fontFiles = gulp.src('./src/bower_modules/components-bootstrap/fonts/*', {
            base: './src/bower_modules/components-bootstrap/'
        });
    return es.concat(combinedCss, fontFiles)
        .pipe(gulp.dest('./dist/'));
});

// Copies index.html, replacing <script> and <link> tags to reference production URLs
gulp.task('html', function() {

    gulp.src('./src/favicon.ico').pipe(gulp.dest('./dist/'));

    return gulp.src('./src/index.html')
        .pipe(htmlreplace({
            'css': 'css.css',
            'js': 'scripts.js'
        }))
        .pipe(gulp.dest('./dist/'));
});

//Copies imgs
gulp.task('imgs', function() {

    gulp.src('./src/bower_modules/DataTables/media/images/**/*').pipe(gulp.dest('./dist/imgs/'));

    return gulp.src('./src/imgs/**/*')
        .pipe(gulp.dest('./dist/imgs/'));
});

// Removes all files from ./dist/
gulp.task('clean', function() {
    return gulp.src('./dist/**/*', {
            read: false
        })
        .pipe(clean());
});

gulp.task('watchless', function() {

    var watcher = gulp.watch('./src/css/**/*.less', ['less']);

    watcher.on('change', function(event) {
        console.log('File ' + event.path + ' was ' + event.type + ', running tasks...');
    });
});

gulp.task('webserver', function() {
    gulp.src('./src/')
        .pipe(server({
            open: true,
            port: 9000
        }));
});

gulp.task('dev', ['less', 'watchless', 'webserver'], function(callback) {
    callback();
    console.log('\nStart server at ' + chalk.magenta('src/\n'));
});

gulp.task('build', ['html', 'js', 'css', 'imgs'], function(callback) {
    callback();
    console.log('\nPlaced optimized files in ' + chalk.magenta('dist/\n'));
});

