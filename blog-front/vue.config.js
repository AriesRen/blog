module.exports = {
    pages: {
        index: {
            entry: 'src/modules/index/main.js',
            template: 'public/index.html',
            filename: 'index.html',
            title: '无名万物',
            chunks: ['chunk-vendors', 'chunk-common', 'index']
        },
        console: {
            entry: 'src/modules/console/main.js',
            template: 'public/console.html',
            filename: 'console.html',
            title: 'console'
        }
    }
}