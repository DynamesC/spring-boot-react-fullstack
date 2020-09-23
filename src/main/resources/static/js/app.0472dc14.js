(function(e){function t(t){for(var o,r,i=t[0],s=t[1],c=t[2],u=0,f=[];u<i.length;u++)r=i[u],Object.prototype.hasOwnProperty.call(n,r)&&n[r]&&f.push(n[r][0]),n[r]=0;for(o in s)Object.prototype.hasOwnProperty.call(s,o)&&(e[o]=s[o]);d&&d(t);while(f.length)f.shift()();return l.push.apply(l,c||[]),a()}function a(){for(var e,t=0;t<l.length;t++){for(var a=l[t],o=!0,i=1;i<a.length;i++){var s=a[i];0!==n[s]&&(o=!1)}o&&(l.splice(t--,1),e=r(r.s=a[0]))}return e}var o={},n={app:0},l=[];function r(t){if(o[t])return o[t].exports;var a=o[t]={i:t,l:!1,exports:{}};return e[t].call(a.exports,a,a.exports,r),a.l=!0,a.exports}r.m=e,r.c=o,r.d=function(e,t,a){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:a})},r.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var a=Object.create(null);if(r.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)r.d(a,o,function(t){return e[t]}.bind(null,o));return a},r.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],s=i.push.bind(i);i.push=t,i=i.slice();for(var c=0;c<i.length;c++)t(i[c]);var d=s;l.push([0,"chunk-vendors"]),a()})({0:function(e,t,a){e.exports=a("56d7")},"034f":function(e,t,a){"use strict";var o=a("85ec"),n=a.n(o);n.a},"17cf":function(e,t,a){"use strict";var o=a("43bd"),n=a.n(o);n.a},"43bd":function(e,t,a){},"56d7":function(e,t,a){"use strict";a.r(t);a("e260"),a("e6cf"),a("cca6"),a("a79d");var o=a("2b0e"),n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{attrs:{id:"app"}},[a("router-view")],1)},l=[],r={name:"app",components:{}},i=r,s=(a("034f"),a("2877")),c=Object(s["a"])(i,n,l,!1,null,null,null),d=c.exports,u=a("8c4f"),f=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"login_container"},[a("div",{staticClass:"login_box"},[e._m(0),a("el-form",{ref:"loginFormRef",staticClass:"login_form",attrs:{"label-width":"0px",model:e.loginForm,rules:e.loginFormRules}},[a("el-form-item",{attrs:{prop:"username"}},[a("el-input",{attrs:{"prefix-icon":"el-icon-message"},model:{value:e.loginForm.username,callback:function(t){e.$set(e.loginForm,"username",t)},expression:"loginForm.username"}})],1),a("el-form-item",{attrs:{prop:"password"}},[a("el-input",{attrs:{"prefix-icon":"el-icon-lock",type:"password"},model:{value:e.loginForm.password,callback:function(t){e.$set(e.loginForm,"password",t)},expression:"loginForm.password"}})],1),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:e.login}},[e._v("Login")]),a("el-button",{attrs:{type:"info"},on:{click:e.resetLoginForm}},[e._v("Reset")])],1)],1)],1)])},h=[function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"avatar_box"},[o("img",{attrs:{src:a("8950"),alt:""}})])}],g=a("bc3a").default,m={data:function(){return{loginForm:{username:"",password:""},loginFormRules:{username:[{required:!0,message:"username should not be null",trigger:"blur"}],password:[{required:!0,message:"password please",trigger:"blur"}]}}},methods:{resetLoginForm:function(){this.loginForm.username="",this.loginForm.password="",this.$refs.loginFormRef.resetFields()},loginFailed:function(){console.log("登录错误"),this.$message({message:"账号密码错误！",type:"error"})},loginSucceeded:function(e){window.sessionStorage.setItem("myToken",e.data.body.token),window.sessionStorage.setItem("userName",this.loginForm.username),this.$message({message:"登陆成功！",type:"success"}),console.log(window.sessionStorage.getItem("myToken")),$.push("/home")},login:function(){var e=this;this.$refs.loginFormRef.validate((function(t){if(console.log("login pressed"),t){var a={username:e.loginForm.username,password:e.loginForm.password},o=e;g.post("V1/Login",a).then((function(e){console.log(e.data.body.token),o.loginSucceeded(e)})).catch((function(e){console.log(e.response),o.loginFailed()}))}}))}}},p=m,b=(a("17cf"),Object(s["a"])(p,f,h,!1,null,"7d7746cb",null)),v=b.exports,_=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"hello"},[a("el-container",{staticStyle:{height:"100%",border:"1px solid #eee"},attrs:{direction:"vertical"}},[a("el-container",[a("el-header",{staticStyle:{"text-align":"right","font-size":"12px",height:"200px"}},[a("el-button",{staticStyle:{position:"absolute",top:"60px",right:"40px",width:"100px"},attrs:{type:"primary",icon:"el-icon-switch-button"},on:{click:e.logout}},[e._v("登出")]),a("el-button",{staticStyle:{position:"absolute",top:"140px",right:"40px"},attrs:{type:"primary"},on:{click:e.changeAdClicked}},[e._v("更换广告")]),a("el-dialog",{attrs:{title:"更换广告",visible:e.showContentChangeDialog},on:{"update:visible":function(t){e.showContentChangeDialog=t}}},[a("el-form",{attrs:{model:e.form}},[a("el-form-item",{attrs:{label:"广告组","label-width":e.formLabelWidth}},[a("el-select",{attrs:{placeholder:"请选择广告"},model:{value:e.form.ad_index,callback:function(t){e.$set(e.form,"ad_index",t)},expression:"form.ad_index"}},e._l(e.ad_data,(function(e){return a("el-option",{key:e.name,attrs:{label:e.name,value:e.index}})})),1)],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.showContentChangeDialog=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:e.confirmAdChange}},[e._v("确 定")])],1)],1)],1)],1),a("el-container",{attrs:{height:"100%"}},[a("el-main",[e.showTable?a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],ref:"multipleTable",attrs:{data:e.tableData,height:"600px","tooltip-effect":"dark"},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{fixed:"left",type:"selection",width:"55",selectable:e.checkSelectable}}),a("el-table-column",{attrs:{prop:"id",label:"ID",width:"140","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"connection_status",label:"在线状态",width:"80","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"device_type",label:"设备类型",width:"80","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"screen_size",label:"屏幕尺寸",width:"80","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"gateway_mac",label:"所属网关",width:"140","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"ad_name",label:"广告名称",width:"140","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"action_count",label:"扫码次数",width:"80","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{prop:"battery_level",label:"电量",width:"80","show-overflow-tooltip":""}})],1):e._e()],1)],1)],1)],1)},w=[],y=(a("4160"),a("45fc"),a("b0c0"),a("ac1f"),a("1276"),a("159b"),a("3835")),x=a("bc3a").default,S={name:"Home",props:{msg:String},created:function(){this.fetchData()},data:function(){return{showTable:!0,showContentChangeDialog:!1,headers:{Authorization:"Bearer "+window.sessionStorage.getItem("myToken")},tableData:[],labelMacs:[],ad_data:[{index:-1,name:"请选择广告"},{index:1,name:"test1",barcode_without_mac:"test1",small_demo_id:"058f8db12ae1482b850da1454f90b988",large_demo_id:"ee603ff46c694cbc826eec0239085c3e"},{index:2,name:"test2",barcode_without_mac:"test2",small_demo_id:"aa39e1f3cef74938b650a6944b8f09e9",large_demo_id:"f765506a1d1546ea8b80f5fb99377ed6"}],loading:!0,multipleSelection:[],param_for_querying_labels:{page:1,size:1e3,storeUuid:"0108"},param_for_querying_gateways:{page:1,size:1e3,storeUuid:"0108"},form:{ad_index:-1},formLabelWidth:"120px"}},methods:{logout:function(){console.log("logout pressed"),window.sessionStorage.removeItem("myToken"),$.push("/login")},fetchData:function(){this.loading=!0,this.labels_fetched=!1,this.gateways_fetched=!1;var e=this;function t(e){return function(t){var a=t.some((function(e){return e.error}));if(a)throw t;return e(t)}}x.all([x.post("V2/pub/label/query",this.param_for_querying_labels,{headers:this.headers,params:this.param_for_querying_labels}),x.get("V2/gateway",{headers:this.headers,params:this.param_for_querying_gateways})]).then(t((function(t){var a=Object(y["a"])(t,2),o=a[0],n=a[1];e.fetchLabelDataSucceeded(o),e.fetchGatewayDataSucceeded(n)}))).catch(x.spread((function(t,a){e.fetchLabelDataFailed(t),e.fetchGatewayDataFailed(a)})))},fetchLabelDataSucceeded:function(e){console.log("label fetching succeeded"),console.log(e.data);var t=e.data.body.list;console.log(t.length+" labels found");for(var a=this,o=0;o<t.length;o++){var n=t[o],l=n.mac;this.labelMacs.push(l);var r=n.battery,i=n.screenSize,s="离线",c="";null!=n.correspond&&(c=n.correspond.gatewayMac),"1"!=n.heartbeat&&"0"!=n.heartbeat&&(s="在线"),this.tableData.push({id:l,battery_level:r,screen_size:this.decodeScreenSize(i),device_type:"屏幕",connection_status:s,gateway_mac:c,ad_name:""})}a.fetchGoodsBinding(),this.loading=!1},fetchLabelDataFailed:function(e){null!=e&&(this.loading=!1,this.$message.error("设备信息拉取失败"),console.log("label fetching failed"))},decodeScreenSize:function(e){return"000c"===e?4.2:"000f"===e?5.83:"not defined"},fetchGatewayDataSucceeded:function(e){console.log("gateway fetching succeeded"),console.log(e.data);var t=e.data.list;console.log(t.length+" gateways found");for(var a=0;a<t.length;a++){var o=t[a],n=o.mac;this.tableData.push({id:n,connection_status:o.mode,device_type:"网关",screen_size:"N/A",battery_level:"N/A"})}this.loading=!1},fetchGatewayDataFailed:function(e){null!=e&&(this.loading=!1,this.$message.error("网关信息拉取失败"),console.log("gateway fetching failed"))},fetchGoodsBinding:function(){var e=this;console.log("fetch goods binding called");var t=[];function a(e){return function(t){var a=t.some((function(e){return e.error}));if(a)throw t;return e(t)}}for(var o=0;o<this.labelMacs.length;o++)t.push(x.get("/V2/label/query",{headers:this.headers,params:{mac:this.labelMacs[o]}}));x.all(t).then(a((function(t){console.log(t);for(var a=0;a<t.length;a++){var o=t[a];if(200==o.data.status){console.log(o);var n=o.data.body.barcode,l=o.data.body.label4,r=o.request.responseURL.split("=")[1];console.log("good result successfully fetched for "+r);for(var i=0;i<e.tableData.length;i++){var s=e.tableData[i];if(s.id==r){console.log(n.substring(0,n.length-12)),s.good_barcode_without_mac=n.substring(0,n.length-12),s.good_barcode=n,s.good_name=l;for(var c=!1,d=0;d<e.ad_data.length;d++){var u=e.ad_data[d];if(u.barcode_without_mac==s.good_barcode_without_mac){s.ad_name=u.name,c=!0;break}}c||(s.ad_name=l);break}}}}e.reloadTable(),e.fetchActionCount()}))).catch(x.spread((function(e){console.log(e)})))},fetchActionCount:function(){var e=this;console.log("fetch action count called");var t=[];function a(e){return function(t){var a=t.some((function(e){return e.error}));if(a)throw t;return e(t)}}for(var o=0;o<e.tableData.length;o++){for(var n=e.tableData[o],l=n.id,r=n.ad_name,i="",s=0;s<e.ad_data.length;s++){var c=e.ad_data[s];if(r==c.name){i=c.barcode_without_mac;break}}""!=i&&(t.push(x.get("/api/landingpages/get-action-count",{params:{adId:i,mac:l},baseURL:"http://81.68.165.124:8080/"})),x.all(t).then(a((function(t){console.log(t);for(var a=0;a<t.length;a++){var o=t[a];console.log(o);var n=o.request.responseURL.split("mac=")[1];n=n.split("&")[0],console.log(n);for(var l=0;l<e.tableData.length;l++){var r=e.tableData[l];r.id==n&&(r.action_count=o.data)}}e.reloadTable()}))).catch(x.spread((function(e){console.log(e)}))))}},fetchLabelTemplateBinding:function(){var e=this,t=[];function a(e){return function(t){var a=t.some((function(e){return e.error}));if(a)throw t;return e(t)}}for(var o=0;o<this.labelMacs.length;o++){var n=this.labelMacs[o];t.push(x.post("/V2/pub/binding/query",{mac:n},{headers:this.headers,params:{mac:n}}))}x.all(t).then(a((function(t){console.log(t);for(var a=0;a<t.length;a++){var o=t[a];if(console.log(o),431!=o.data.status){var n=o.data.body.templets[0].demoId,l=o.data.body.templets[0].demoName,r=o.request.responseURL.split("=")[1];console.log(r);for(var i=0;i<e.tableData.length;i++){var s=e.tableData[i];if(s.id==r){s.demo_id=n,s.demo_name=l;break}}e.reloadTable()}}}))).catch(x.spread((function(e){console.log(e)})))},changeAdClicked:function(){0==this.multipleSelection.length?this.$message.error("请至少选择一个屏幕"):this.showContentChangeDialog=!0},confirmAdChange:function(){if(this.showContentChangeDialog=!1,-1!=this.form.ad_index){for(var e=this.ad_data[this.form.ad_index],t=[],a=0;a<this.multipleSelection.length;a++){var o=this.multipleSelection[a].id,n=e.small_demo_id;5.83==this.multipleSelection[a].screen_size&&(n=e.large_demo_id);var l={mac:o,barcode:e.barcode_without_mac+o,demoId:n,storeId:"0108"};t.push(x.post("/V2/pub/bind/update",l,{headers:this.headers}))}var r=this.$loading({lock:!0,text:"Loading",spinner:"el-icon-loading",background:"rgba(0, 0, 0, 0.7)"}),i=this;x.all(t).then(s((function(e){console.log(e),r.close(),i.fetchGoodsBinding()}))).catch(x.spread((function(e){console.log(e),r.close()}))),this.form.ad_index=-1}function s(e){return function(t){var a=t.some((function(e){return e.error}));if(a)throw t;return e(t)}}},reloadTable:function(){var e=this;this.showTable=!1,this.$nextTick((function(){e.showTable=!0}))},checkSelectable:function(e){return"网关"!=e.device_type&&"在线"==e.connection_status},toggleSelection:function(e){var t=this;e?e.forEach((function(e){t.$refs.multipleTable.toggleRowSelection(e)})):this.$refs.multipleTable.clearSelection()},handleSelectionChange:function(e){this.multipleSelection=e},tableRowClassName:function(e){var t=e.row,a=e.rowIndex;if("在线"!=t.connection_status)return console.log(a),{"background-color":"#e9c8c3"}}}},k=S,D=(a("dd10"),Object(s["a"])(k,_,w,!1,null,"7c91efe1",null)),C=D.exports;o["default"].use(u["a"]);var F=[{path:"",component:v,meta:{title:"登录-云云说后台"}},{path:"/login",component:v,meta:{title:"登录-云云说后台"}},{path:"/home",component:C,meta:{title:"主页-云云说后台"}}],T=new u["a"]({routes:F});T.beforeEach((function(e,t,a){var o=window.sessionStorage.getItem("myToken");return"/login"===e.path?o?a(!1):(document.title=e.meta.title,a()):o?(document.title=e.meta.title,void a()):(document.title="登录-云云说后台",a("/login"))})),T.afterEach((function(e,t){e.meta.title&&(document.title=e.meta.title)}));var $=T,L=a("bc3a"),O=a.n(L),j=(a("0fb7"),a("450d"),a("f529")),q=a.n(j),R=(a("672e"),a("101e")),I=a.n(R),z=(a("0c67"),a("299c")),M=a.n(z),A=(a("e960"),a("b35b")),E=a.n(A),G=(a("5466"),a("ecdf")),P=a.n(G),U=(a("38a0"),a("ad41")),N=a.n(U),V=(a("f4f9"),a("c2cc")),B=a.n(V),J=(a("7a0f"),a("0f6c")),W=a.n(J),H=(a("b8e0"),a("a4c4")),K=a.n(H),Q=(a("b84d"),a("c216")),X=a.n(Q),Y=(a("8f24"),a("76b9")),Z=a.n(Y),ee=(a("ce18"),a("f58e")),te=a.n(ee),ae=(a("34db"),a("3803")),oe=a.n(ae),ne=(a("8bd8"),a("4cb2")),le=a.n(ne),re=(a("4ca3"),a("443e")),ie=a.n(re),se=(a("de31"),a("c69e")),ce=a.n(se),de=(a("a769"),a("5cc3")),ue=a.n(de),fe=(a("a673"),a("7b31")),he=a.n(fe),ge=(a("adec"),a("3d2d")),me=a.n(ge),pe=(a("10cb"),a("f3ad")),be=a.n(pe),ve=(a("eca7"),a("3787")),_e=a.n(ve),we=(a("6611"),a("e772")),ye=a.n(we),xe=(a("1f1a"),a("4e4b")),Se=a.n(xe),ke=(a("be4f"),a("896a")),De=a.n(ke),Ce=(a("a7cc"),a("df33")),Fe=a.n(Ce),Te=(a("915d"),a("e04d")),$e=a.n(Te),Le=(a("425f"),a("4105")),Oe=a.n(Le),je=(a("1951"),a("eedf")),qe=a.n(je);o["default"].use(qe.a),o["default"].use(Oe.a),o["default"].use($e.a),o["default"].use(Fe.a),o["default"].use(De.a),o["default"].use(Se.a),o["default"].use(ye.a),o["default"].use(_e.a),o["default"].use(be.a),o["default"].use(me.a),o["default"].use(he.a),o["default"].use(ue.a),o["default"].use(ce.a),o["default"].use(ie.a),o["default"].use(le.a),o["default"].use(oe.a),o["default"].use(te.a),o["default"].use(Z.a),o["default"].use(X.a),o["default"].use(K.a),o["default"].use(W.a),o["default"].use(B.a),o["default"].use(N.a),o["default"].use(P.a),o["default"].use(E.a),o["default"].use(M.a),o["default"].use(I.a),o["default"].prototype.$message=q.a;a("aede");o["default"].config.productionTip=!1,O.a.defaults.baseURL="http://esl.ylwlesl.com:9191/",new o["default"]({router:$,render:function(e){return e(d)}}).$mount("#app")},"85ec":function(e,t,a){},8950:function(e,t,a){e.exports=a.p+"img/yys_icon.5b7df981.png"},aa17:function(e,t,a){},aede:function(e,t,a){},dd10:function(e,t,a){"use strict";var o=a("aa17"),n=a.n(o);n.a}});
//# sourceMappingURL=app.0472dc14.js.map