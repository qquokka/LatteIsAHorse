config => {
  config.plugin("html").tap(args => {
    
    args[0].meta = {
      viewport: "width=device-width,initial-scale=1,user-scalable=no"
    };

    return args;
  });
};
// module.exports = {
//   devServer: {
//     open: process.platform === 'darwin',
//     host: 'localhost',
//     port: 8080, // CHANGE YOUR PORT HERE!
//     // https: false,
//     // https: {
//     //   key: '',
//     //   cert: '',
//     //   ca: '',
//     // },
//     hotOnly: false,
//   },
// }