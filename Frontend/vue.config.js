config => {
  config.plugin("html").tap(args => {
    args[0].meta = {
      viewport: "width=device-width,initial-scale=1,user-scalable=no"
    };
    return args;
  });
};
var fs = require('fs');
module.exports = {
  devServer: {
    host: 'localhost',
    port: 443,
    https: true,
    https: {
      key: fs.readFileSync('privkey.pem'),
      cert: fs.readFileSync('cert.pem'),
      ca: fs.readFileSync('chain.pem'),
    },
    hotOnly: false,
  },
  pwa: {
    name: '라떼는말이야',
    themeColor: '#4DBA87',
    msTileColor: '#000000',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: 'black',
    workboxPluginMode: 'InjectManifest',
    workboxOptions: {
      swSrc: 'dev/sw.js',
    }
  }
}