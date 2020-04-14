/**
 * Project Gruntfile.js
 * Grunt module definition
 * @param  {Object} grunt Grunt
 */
module.exports = function (grunt) {
  // Project configuration.
  grunt.initConfig({

    // package.json metadata.
    pkg: grunt.file.readJSON('package.json'),

    eslint: {
      target: [
        'Gruntfile.js',
        'source/js/*.js'
      ]
    },

    concat: {
      options: {
        separator: '\n;\n', // jump a line and add a ; to make sure scripts concat fine
        sourceMap: false
      },
      contrib_src: {
        src: [
          // JQuery
          'node_modules/jquery/dist/jquery.js',
          'node_modules/jquery.cookie/jquery.cookie.js',
          // Bootstrap
          'node_modules/bootstrap/js/transition.js',
          'node_modules/bootstrap/js/alert.js',
          'node_modules/bootstrap/js/button.js',
          'node_modules/bootstrap/js/carousel.js',
          'node_modules/bootstrap/js/collapse.js',
          'node_modules/bootstrap/js/dropdown.js',
          'node_modules/bootstrap/js/modal.js',
          'node_modules/bootstrap/js/tooltip.js',
          'node_modules/bootstrap/js/popover.js',
          'node_modules/bootstrap/js/scrollspy.js',
          'node_modules/bootstrap/js/tab.js',
          'node_modules/bootstrap/js/affix.js',
          // Bootstrap datepicker
          'node_modules/bootstrap-datepicker/js/bootstrap-datepicker.js',
          // Bootstrap switch
          'node_modules/bootstrap-switch/dist/js/bootstrap-switch.js',
          // Bootbox for Bootstrap
          'node_modules/bootbox/bootbox.js',
          // Select2 Js
          'node_modules/select2/dist/js/select2.full.js',
          // underscoreJs
          'node_modules/underscore/underscore.js',
          'node_modules/underscore.string/dist/underscore.string.js',
          'node_modules/chart.js/dist/Chart.js',
          'source/js/contrib/underscore.template-helpers.js'
        ],
        dest: '../../../../../target/classes/static/assets/source/debug/js/contrib.js'
      },
      contrib_min: {
        src: [
          // JQuery
          'node_modules/jquery/dist/jquery.min.js',
          'node_modules/jquery.cookie/jquery.cookie.js',
          // Bootstrap
          'node_modules/bootstrap/dist/js/bootstrap.min.js',
          // Bootstrap datepicker
          'node_modules/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js',
          // Bootstrap switch
          'node_modules/bootstrap-switch/dist/js/bootstrap-switch.min.js',
          // Bootbox for Bootstrap
          'node_modules/bootbox/bootbox.min.js',
          // Select2 Js
          'node_modules/select2/dist/js/select2.full.min.js',
          // underscoreJs
          'node_modules/underscore/underscore-min.js',
          'node_modules/underscore.string/dist/underscore.string.min.js',
          'source/js/contrib/underscore.template-helpers.js',
          'node_modules/chart.js/dist/Chart.min.js'
          ],
        dest: '../../../../../target/classes/static/assets/js/min/contrib.min.js'
      },
      supplier: {
        src: [
          // App
          'source/js/project.js'
        ],
        dest: '../../../../../target/classes/static/assets/source/debug/js/project.js'
      }
    },
    // Minify our custom Js
    uglify: {
      options: {
        ie8: true,
        sourceMap: true
      },
      js: {
        files: {
          '../../../../../target/classes/static/assets/js/min/project.min.js': ['../../../../../target/classes/static/assets/source/debug/js/project.js']
        }
      }
    },

    // LESS & CSS
    less: {
      css: {
        files: {
          // Compile our less files
          '../../../../../target/classes/static/assets/source/debug/css/style.css': [
            'source/less/style.less'
          ]
        }
      }
    },
    // autoprefixer to ensure cross-brower compatiblititly
    autoprefixer: {
      css: {
        options: {
          browsers: [
            'Android 2.3',
            'Android >= 4',
            'Chrome >= 20',
            'Firefox >= 24',
            'Explorer >= 8',
            'iOS >= 6',
            'Opera >= 12',
            'Safari >= 6'
          ]
        },
        files: {
          '../../../../../target/classes/static/assets/source/debug/css/style.css': [
            '../../../../../target/classes/static/assets/source/debug/css/style.css'
          ]
        }
      }
    },
    // Minify CSS
    cssmin: {
      options: {
        sourceMap: true
      },
      target: {
        files: {
          '../../../../../target/classes/static/assets/source/css/style.min.css': [
            '../../../../../target/classes/static/assets/source/debug/css/style.css'
          ]
        }
      }
    },

    sync: {
      main: {
        files: [
          {cwd: 'node_modules/bootstrap/fonts/', src: ['*.*'], dest: 'fonts/'},// includes files in path and its subdirs
          {cwd: 'node_modules/font-awesome/fonts/', src: ['*.*'], dest: 'fonts/'} // includes files in path and its subdirs
        ],
        verbose: true, // Default: false
        pretend: false, // Don't do any disk operations - just write log. Default: false
        failOnError: true, // Fail the task when copying is not possible. Default: false
        updateAndDelete: false, // Remove all files from dest that are not found in src. Default: false
        compareUsing: "md5" // compares via md5 hash of file contents, instead of file modification time. Default: "mtime"

      }
    },
    // Watch for changes
    watch: {
      js: {
        files: [
          'source/js/*.js',
          'source/debug/js/*.js'
        ],
        tasks: ['concat', 'uglify']
      },
      less: {
        files: 'source/less/*.less',
        tasks: ['less', 'autoprefixer', 'cssmin']
      }
    }

  });

  // Load NPM tasks
  grunt.loadNpmTasks('grunt-autoprefixer');
  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-less');
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-watch');
  grunt.loadNpmTasks('grunt-eslint');
  grunt.loadNpmTasks('grunt-sync');

  // `default` goal builds & watches for changes
  grunt.registerTask('default', [
    'concat',
    'uglify',
    'less',
    'autoprefixer',
    'cssmin',
    'sync',
    'watch'
  ]);
  // `build` only builds the assets
  grunt.registerTask('build', [
    'concat',
    'uglify',
    'less',
    'autoprefixer',
    'cssmin',
    'sync'
  ]);
};
