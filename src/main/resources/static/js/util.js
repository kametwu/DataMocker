
const util = {

  getValue(obj, key, defaultValue) {
    let val;
    if(oss.isObject(obj)) {
      if(oss.isString(key) || oss.isNumber(key)) {
        val = obj[key];
      }else if(oss.isArray(key)) {
        val = obj;
        for(let k of key) {
          if(!oss.isObject(val) || !(k in val)) {
            val = null;
            break;
          }
          val = val[k];
        }
      }
      return val || defaultValue;
    }
  },

  download: (filename, data) => {
	if(util.isObject(data)) data = JSON.stringify(data);
    const blob = new Blob([data]);
    if('msSaveOrOpenBlob' in navigator){
      window.navigator.msSaveOrOpenBlob(blob,filename);
    }else{
      var downloadElement = document.createElement('a');
      var href = window.URL.createObjectURL(blob); //创建下载的链接
      downloadElement.href = href;
      downloadElement.download = filename; //下载后文件名
      document.body.appendChild(downloadElement);
      downloadElement.click(); //点击下载
      document.body.removeChild(downloadElement); //下载完成移除元素
      window.URL.revokeObjectURL(href); //释放掉blob对象
    }
  },

  isFunction: val => {
    return Object.prototype.toString.call(val) === '[object Function]';
  },
  
  isArray: val => {
    return Object.prototype.toString.call(val) === '[object Array]';
  },

  isDate: val => {
    return Object.prototype.toString.call(val) === '[object Date]';
  },

  isObject: val => {
    return val !== null && typeof val === 'object';
  },

  isString: val => {
    return typeof val === 'string';
  },

  isNumber: val => {
    return typeof val === 'number';
  },
  
  isUndefined: val => {
    return typeof val === 'undefined';
  },

  isNull: val => {
    return val === null || util.isUndefined(val);
  },
  
  isEmpty: val => {
    return util.isNull(val) || val === '';
  },
  
  UUID: () => {
    function S4() {
      return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return S4() + S4() + new Date().getTime() + S4() + S4();
  }

};