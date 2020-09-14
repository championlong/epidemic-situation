"use strict";

var _vue = _interopRequireDefault(require("vue"));

var _App = _interopRequireDefault(require("./App"));

var _router = _interopRequireDefault(require("./router"));

var _store = _interopRequireDefault(require("./store"));

var _lodash = _interopRequireDefault(require("lodash"));

var _elementUi = _interopRequireDefault(require("element-ui"));

require("@/assets/theme/index.css");

require("@/assets/icon/iconfont.css");

var _time = require("./utils/time");

var _vueBaiduMap = _interopRequireDefault(require("vue-baidu-map"));

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { "default": obj }; }

_vue["default"].config.productionTip = false;

_vue["default"].use(_elementUi["default"]);

_vue["default"].use(_vueBaiduMap["default"], {
  ak: 'k8FwPDpAMU82EQjFdXy1uGeegXGL00dh'
});

Object.defineProperty(_vue["default"].prototype, '$_', {
  value: _lodash["default"]
});

_vue["default"].directive('title', function (el, binding) {
  document.title = el.dataset.title;
}); // 格式话时间


_vue["default"].filter('format', _time.formatTime);

new _vue["default"]({
  el: '#app',
  router: _router["default"],
  store: _store["default"],
  template: '<App/>',
  components: {
    App: _App["default"]
  }
});
//# sourceMappingURL=main.dev.js.map
