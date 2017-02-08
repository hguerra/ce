var gulp = require('gulp');
var connect = require('gulp-connect');
var jshint = require('gulp-jshint');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var rename = require('gulp-rename');
var cleancss = require('gulp-clean-css');

var output = './dist';
var htmlfiles = './src/{,*/}*.html';
var jsfiles = './src/js/{,*/}*.js';
var cssfiles = './src/css/{,*/}*.css';

gulp.task('connect', function() {
	connect.server({
		root: 'src',
		port:8082,
		livereload: true
	});
});

gulp.task('html', function () {
	gulp.src(htmlfiles)
		.pipe(connect.reload());
});

gulp.task('js', function () {
	gulp.src(jsfiles)
		.pipe(connect.reload());
});

gulp.task('css', function () {
	gulp.src(cssfiles)
		.pipe(connect.reload());
});

gulp.task('lint', function() {
	gulp.src(jsfiles)
		.pipe(jshint())
		.pipe(jshint.reporter('default'));
});

gulp.task('distjs', function() {
	gulp.src(jsfiles)
		.pipe(rename({suffix: '.min'}))
		.pipe(uglify())
		.pipe(gulp.dest(output));
});

gulp.task('distcss', function() {
	gulp.src(cssfiles)
		.pipe(rename({suffix: '.min'}))
		.pipe(cleancss({compatibility: 'ie8'}))
		.pipe(gulp.dest(output));
});

gulp.task('watch', function () {
	gulp.watch([htmlfiles], ['html']);
	gulp.watch([jsfiles], ['js']);
	gulp.watch([cssfiles], ['css']);
});

gulp.task('default', ['connect', 'watch']);