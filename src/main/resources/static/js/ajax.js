
const CANCEL_TOKEN = axios.CancelToken;
const AXIOS_SOURCE = CANCEL_TOKEN.source();
const vue = new Vue();

const ajax = {
  inject() {
    // 配置编码
	  axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
//	  axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
	  axios.defaults.headers.post['X-Requested-With'] = 'XMLHttpRequest';
  },

  /**
   * 负责post请求
   * @param url ：请求的路径
   * @param params ：请求的参数
   * @param obj ：上传、下载的动作参数
   * @returns {Promise}
   */
  post(url, params, obj) {
    return new Promise(function (resolve, reject) {
      if (url && url.indexOf('/') === 0) {
        url = url.substr(1);
      }
      let config = {};
      if (obj && obj.action == 'upload') {
        config.headers = {'Content-Type': 'multipart/form-data'};
      } else if (obj && obj.action == 'download') {
        config.responseType = 'blob';
      } else {
        obj = true;
//        config.transformRequest = [item => {
//          return Qs.stringify(item, {arrayFormat: 'repeat'});
//        }];
      }
      if (obj && obj.action == 'upload' || obj && obj.action == 'download') {
        let form = new FormData();
        for (let d in params) {
          let v = params[d];
          if (Object.prototype.toString.call(v) === '[object Array]') {
            v.forEach(function (item) {
              form.append(d, item);
            });
          } else {
            form.append(d, v);
          }
        }
        params = form;
      }

      axios.post(url, params, config).then((res) => {
        let _data = res.data;
        if (util.isObject(obj) && obj.action == 'download') {
          if (util.getValue(res, ['headers', 'content-type'], '').indexOf('application/octet-stream') != -1) {

            // 20190620 何鹏举 IE浏览器报错, 更换其他请求头处理(original-filename-encode)
            let filename = decodeURIComponent(res.headers['original-filename-encode']);

            util.download(filename, res.data);
            resolve(_data);
          } else {
            let errBlob = new Blob([res.data], {type: 'application/json'});
            let fr = new FileReader();
            fr.readAsText(errBlob);
            fr.onload = function () {
              const err = JSON.parse(this.result);
              vue.$msgbox({type: 'error', title: '错误提示', message: err.errmsg, errDetail: err.errdetail});
            }
          }
        } else if (_data.code === 0) {
          resolve(_data);
        } else if (_data.code === 60113) {
          vue.$msgbox({
            type: 'warning', title: '登录提示', message: '登录会话失效，请重新登录后再操作', showClose: false, confirmButtonText: '重新登录', callback: action => {
              sessionStorage.removeItem('OSSGlobalData');
//              oss.__vue__.$router.push({name: 'login'});
              vue.$message('跳转到登录页面');
            }
          });
          resolve(res);
        } else if (_data.code != -1) {
          vue.$msgbox({type: 'info', title: '系统提示', message: _data.msg, errDetail: _data.detailMsg});
          reject(res);
        } else {
          vue.$msgbox({type: 'error', title: _data.detailMsg ? '服务器异常' : '系统异常', message: _data.msg, errDetail: _data.detailMsg});
          reject(res);
        }
      }).catch((err) => {
        let errMsg = err.response ? '(' + err.response.status + ')糟糕，服务器不见了！' : err.message;
        let errTitle = err.response ? '服务器异常' : '系统异常';
        if (util.isFunction(err)) {
          err(errTitle + '-' + errMsg);
        } else {
          vue.$msgbox({type: 'error', title: errTitle, message: errMsg, errDetail: errMsg});
        }
        reject(err);
      });
    });
  },
  /**
   * 负责get请求
   * @param url ：请求的路径
   * @param params ：请求的参数
   * @param LoadingObj ：是否loading
   * @param downloadObj ：上传、下载的动作参数
   * @returns {Promise}
   */
  get(url, params, LoadingObj, downloadObj) {
    return new Promise(function (resolve, reject) {
      if (url && url.indexOf('/') === 0) {
        url = `${url.substr(1)}?currentTime=${Date.now()}`;    //加时间戳，避免IE浏览器get请求的缓存问题
      }
      let config = {};
      if (downloadObj && downloadObj.action == 'download') {
        config.responseType = 'blob';
        // config.responseType = 'arraybuffer';
        config.headers = downloadObj.downloadParams;
        config.params = params;
      }else{
        config.params = params;
      }

      axios.get(url, config).then((res) => {
        let _data = res.data;
        if (downloadObj && downloadObj.action == 'download') {
          if (util.getValue(res, ['headers', 'content-type'], '').indexOf('application/octet-stream') != -1) {
            // 20190620 何鹏举 IE浏览器报错, 更换其他请求头处理(original-filename-encode)
            let filename = decodeURIComponent(res.headers['original-filename-encode']);
            util.download(filename, _data);
            resolve(_data);
          } else {
            let errBlob = new Blob([_data], {type: 'application/json'});
            let fr = new FileReader();
            fr.readAsText(errBlob);
            fr.onload = function () {
              const err = JSON.parse(this.result);
              vue.$msgbox({type: 'error', title: '错误提示', message: err.errmsg, errDetail: err.errdetail});
            }
          }
        } else if (_data.code === 0) {
          resolve(_data);
        } else if (_data.code === 60113) {
          vue.$msgbox({
            type: 'warning', title: '登录提示', message: '登录会话失效，请重新登录后再操作', showClose: false, confirmButtonText: '重新登录', callback: action => {
              sessionStorage.removeItem('OSSGlobalData');
    //        oss.__vue__.$router.push({name: 'login'});
              vue.$message('跳转到登录页面');
            }
          });
        } else if (_data.code != -1) {
          vue.$msgbox({type: 'info', title: '系统提示', message: _data.msg, errDetail: _data.detailMsg});
          reject(res);
        } else {
          vue.$msgbox({type: 'error', title: _data.detailMsg ? '服务器异常' : '系统异常', message: _data.msg, errDetail: _data.detailMsg});
          reject(res);
        }
      }).catch((err) => {
        let errMsg = err.response ? '(' + err.response.status + ')糟糕，服务器不见了！' : err.message;
        let errTitle = err.response ? '服务器异常' : '系统异常';
        if (util.isFunction(err)) {
          err(errTitle + '-' + errMsg);
        } else {
          vue.$msgbox({type: 'error', title: errTitle, message: errMsg, errDetail: errMsg});
        }
        reject(err);
      });
    });
  },

  /* 取消请求  */
  __cancel() {
    AXIOS_SOURCE && AXIOS_SOURCE.cancel('AXIOS_CANCEL');
  }
};

ajax.inject();

//export default ajax;
