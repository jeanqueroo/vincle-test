// Karma configuration file, see link for more information
// https://karma-runner.github.io/1.0/config/configuration-file.html

module.exports = function (config) {

    const coveragePath = require('path')
        .join(__dirname, 'coverage');


    config.set({
        basePath: '',
        frameworks: [
            'jasmine',
            '@angular-devkit/build-angular'
        ],
        plugins: [
            require('@angular-devkit/build-angular/plugins/karma'),
            require('karma-chrome-launcher'),
            require('karma-coverage'),
            require('karma-jasmine'),
            require('karma-jasmine-html-reporter'),
            require('karma-sonarqube-unit-reporter'),
            require('karma-spec-reporter')
        ],
        client: {
            jasmine: {
            
            },
            clearContext: true // leave Jasmine Spec Runner output visible in browser
        },
        jasmineHtmlReporter: {
            suppressAll: true // removes the duplicated traces
        },
        coverageReporter: {
            dir: coveragePath,
            subdir: '.',
            reporters: [
                {type: 'html'},
                {type: 'text-summary'},
                {type: 'lcovonly'}
            ]
        },
        sonarQubeUnitReporter: {
            outputDir: coveragePath,
            outputFile: 'test-results.xml',
            overrideTestDescription: true,
            useBrowserName: false
        },
        reporters: [
            'coverage',
            'progress',
            'sonarqubeUnit',
            'spec'
        ],
        colors: true,
        logLevel: config.LOG_INFO,
        browsers: ['ChromeHeadless'],
        singleRun: true,
       
    });
};
