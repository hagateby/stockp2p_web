/*!
 * jquery-powerFloat.js
 * jQuery 万能浮动层插件
 * http://www.zhangxinxu.com/wordpress/?p=1328
 * © by zhangxinxu  
 * 2010-12-06 v1.0.0插件编写，初步调试
 * 2010-12-30 v1.0.1限定尖角字符字体，避免受浏览器自定义字体干扰
 * 2011-01-03 v1.1.0修复连续获得焦点显示后又隐藏的bug
 修复图片加载正则判断不准确的问题
 * 2011-02-15 v1.1.1关于居中对齐位置判断的特殊处理
 * 2011-04-15 v1.2.0修复浮动层含有过高select框在IE下点击会隐藏浮动层的问题，同时优化事件绑定
 * 2011-09-13 v1.3.0 修复两个菜单hover时间间隔过短隐藏回调不执行的问题
 * 2012-01-13 v1.4.0去除ajax加载的存储
                    修复之前按照ajax地址后缀判断是否图片的问题
修复一些脚本运行出错
修复hover延时显示时，元素没有显示但鼠标移出依然触发隐藏回调的问题
 * 2012-02-27 v1.5.0为无id容器创建id逻辑使用错误的问题
 修复无事件浮动出现时同页面点击空白区域浮动层不隐藏的问题
修复点击与hover并存时特定时候o.trigger报为null错误的问题
 * 2012-03-29 v1.5.1修复连续hover时候后面一个不触发显示的问题
 * 2012-05-02 v1.5.2点击事件 浮动框再次点击隐藏的问题修复
 * 2012-11-02 v1.6.0兼容jQuery 1.8.2
 * 2012-01-28 v1.6.1target参数支持funtion类型，以实现类似动态Ajax地址功能
 */

(function($) {
	var targetObj = new Map();
	$.fn.powerFloat = function(options) {
		return $(this).each(function() {
			targetObj.put(options.targetField, options.target);
			var s = $.extend({}, defaults, options || {});
			var init = function(pms, trigger) {
				if (o.target && o.target.css("display") !== "none") {
					o.targetHide();
				}
				o.s = pms;
				o.trigger = trigger;
			};

			$(this).dblclick(function(e) {
				if (o.display && o.trigger && e.target === o.trigger.get(0)) {
					o.flagDisplay = false;
					o.displayDetect();
				} else {
					init(s, $(this));
					o.getTarget(e.currentTarget.value);
					o.targetGet();

					if (!$('#' + o.s.targetField).data("blurBind")) {
						$('#' + o.s.targetField).bind("blur", function() {
							o.flagDisplay = false;
							o.displayDetect();
							return false;
						}).data("blurBind", true);
					}
				}
			});

			$(this).keyup(function(e) {
				if (o.display && o.trigger && e.target === o.trigger.get(0)) {
					o.flagDisplay = false;
					o.displayDetect();
					init(s, $(this));
					o.getTarget(e.currentTarget.value);
					o.targetGet();
				} else {
					init(s, $(this));
					o.getTarget(e.currentTarget.value);
					o.targetGet();

					if (!$('#' + o.s.targetField).data("blurBind")) {
						$('#' + o.s.targetField).bind("blur", function() {
							o.flagDisplay = false;
							o.displayDetect();
							return false;
						}).data("blurBind", true);
					}
				}
			});
		});
	};

	var o = {
		targetGet : function() {
			// 一切显示的触发来源
			if (!this.trigger) {
				return this;
			}
			var attr = this.trigger.attr(this.s.targetAttr), target = typeof this.s.target == "function" ? this.s.target
					.call(this.trigger)
					: this.s.target;

			// 下拉列表
			var targetHtml = '<ul class="float_list_ul">', arrLength;
			var targetFeild = this.s.targetField;
			if ($.isArray(target) && (arrLength = target.length)) {
				$
						.each(
								target,
								function(i, obj) {
									var sugStr = "", strClass = "", strOnMouseDown = "";
									if (i === 0) {
										strClass = ' class="float_list_li_first"';
									}
									if (i === arrLength - 1) {
										strClass = ' class="float_list_li_last"';
									}
									if (o.s.sugType === "value") {
										sugStr = obj.value;
									} else if (o.s.sugType === "key") {
										sugStr = obj.key;
									} else {
										sugStr = obj.key + '-' + obj.value;
									}
									if (o.s.showType === "value") {
										strOnMouseDown = '"document.getElementById(\''
												+ targetFeild
												+ '\').value = \''
												+ obj.value
												+ '\';"';
									} else {
										strOnMouseDown = '"document.getElementById(\''
												+ targetFeild
												+ '\').value = \''
												+ obj.key
												+ '\';"';
									}
									if (sugStr) {
										targetHtml += '<li onmousedown='
												+ strOnMouseDown
												+ strClass
												+ '><a href="javascript:undefined" class="float_list_a">'
												+ sugStr + '</a></li>';
									}
								});
			} else {
				targetHtml += '<li class="float_list_null">列表无数据。</li>';
			}
			targetHtml += '</ul>';
			o.target = $(targetHtml);
			this.targetProtect = false;
			o.targetShow();
			return this;
		},
		container : function() {
			// 容器(如果有)重装target
			var cont = this.s.container, mode = this.s.targetMode || "mode";
			this.s.sharpAngle = false;

			if (mode !== "common") {
				// common模式无新容器装载
				if (cont === null) {
					cont = "plugin";
				}
				if (cont === "plugin") {
					if (!$("#floatBox_" + mode).size()) {
						$('<div id="floatBox_' + mode + '" class="float_'
										+ mode + '_box"></div>').appendTo(
								$("body")).hide();
					}
					cont = $("#floatBox_" + mode);
				}

				if (cont && typeof (cont) !== "string" && cont.size()) {
					if (this.targetProtect) {
						o.target.show().css("position", "static");
					}
					o.target = cont.empty().append(o.target);
				}
			}
			return this;
		},
		setWidth : function() {
			this.target.width(this.trigger.width());
			return this;
		},
		position : function() {
			if (!this.trigger || !this.target) {
				return this;
			}
			var pos, tri_h = 0, tri_w = 0, cor_w = 0, cor_h = 0, tri_l, tri_t, tar_l, tar_t, cor_l, cor_t, tar_h = this.target
					.data("height"), tar_w = this.target.data("width"), st = $(
					window).scrollTop(),

			off_x = parseInt(this.s.offsets.x, 10) || 0, off_y = parseInt(
					this.s.offsets.y, 10) || 0, mousePos = this.cacheData;

			// 缓存目标对象高度，宽度，提高鼠标跟随时显示性能，元素隐藏时缓存清除
			if (!tar_h) {
				tar_h = this.target.outerHeight();
				if (this.s.hoverFollow) {
					this.target.data("height", tar_h);
				}
			}
			if (!tar_w) {
				tar_w = this.target.outerWidth();
				if (this.s.hoverFollow) {
					this.target.data("width", tar_w);
				}
			}

			pos = this.trigger.offset();
			tri_h = this.trigger.outerHeight();
			tri_w = this.trigger.outerWidth();
			tri_l = pos.left;
			tri_t = pos.top;

			var funMouseL = function() {
				if (tri_l < 0) {
					tri_l = 0;
				} else if (tri_l + tri_h > $(window).width()) {
					tri_l = $(window).width() - tri_w;
				}
			}, funMouseT = function() {
				if (tri_t < 0) {
					tri_t = 0;
				} else if (tri_t + tri_h > $(document).height()) {
					tri_t = $(document).height() - tri_h;
				}
			};

			var arrLegalPos = [ "4-1", "1-4", "5-7", "2-3", "2-1", "6-8",
					"3-4", "4-3", "8-6", "1-2", "7-5", "3-2" ], align = this.s.position, alignMatch = false, strDirect;
			$.each(arrLegalPos, function(i, n) {
				if (n === align) {
					alignMatch = true;
					return;
				}
			});
			if (!alignMatch) {
				align = "4-1";
			}

			var funDirect = function(a) {
				var dir = "bottom";
				// 确定方向
				switch (a) {
				case "1-4":
				case "5-7":
				case "2-3": {
					dir = "top";
					break;
				}
				case "2-1":
				case "6-8":
				case "3-4": {
					dir = "right";
					break;
				}
				case "1-2":
				case "8-6":
				case "4-3": {
					dir = "left";
					break;
				}
				case "4-1":
				case "7-5":
				case "3-2": {
					dir = "bottom";
					break;
				}
				}
				return dir;
			};

			// 居中判断
			var funCenterJudge = function(a) {
				if (a === "5-7" || a === "6-8" || a === "8-6" || a === "7-5") {
					return true;
				}
				return false;
			};

			var funJudge = function(dir) {
				var totalHeight = 0, totalWidth = 0, flagCorner = (o.s.sharpAngle && o.corner) ? true
						: false;
				if (dir === "right") {
					totalWidth = tri_l + tri_w + tar_w + off_x;
					if (flagCorner) {
						totalWidth += o.corner.width();
					}
					if (totalWidth > $(window).width()) {
						return false;
					}
				} else if (dir === "bottom") {
					totalHeight = tri_t + tri_h + tar_h + off_y;
					if (flagCorner) {
						totalHeight += o.corner.height();
					}
					if (totalHeight > st + $(window).height()) {
						return false;
					}
				} else if (dir === "top") {
					totalHeight = tar_h + off_y;
					if (flagCorner) {
						totalHeight += o.corner.height();
					}
					if (totalHeight > tri_t - st) {
						return false;
					}
				} else if (dir === "left") {
					totalWidth = tar_w + off_x;
					if (flagCorner) {
						totalWidth += o.corner.width();
					}
					if (totalWidth > tri_l) {
						return false;
					}
				}
				return true;
			};
			// 此时的方向
			strDirect = funDirect(align);

			if (this.s.sharpAngle) {
				// 创建尖角
				this.createSharp(strDirect);
			}

			// 边缘过界判断
			if (this.s.edgeAdjust) {
				// 根据位置是否溢出显示界面重新判定定位
				if (funJudge(strDirect)) {
					// 该方向不溢出
					(function() {
						if (funCenterJudge(align)) {
							return;
						}
						var obj = {
							top : {
								right : "2-3",
								left : "1-4"
							},
							right : {
								top : "2-1",
								bottom : "3-4"
							},
							bottom : {
								right : "3-2",
								left : "4-1"
							},
							left : {
								top : "1-2",
								bottom : "4-3"
							}
						};
						var o = obj[strDirect], name;
						if (o) {
							for (name in o) {
								if (!funJudge(name)) {
									align = o[name];
								}
							}
						}
					})();
				} else {
					// 该方向溢出
					(function() {
						if (funCenterJudge(align)) {
							var center = {
								"5-7" : "7-5",
								"7-5" : "5-7",
								"6-8" : "8-6",
								"8-6" : "6-8"
							};
							align = center[align];
						} else {
							var obj = {
								top : {
									left : "3-2",
									right : "4-1"
								},
								right : {
									bottom : "1-2",
									top : "4-3"
								},
								bottom : {
									left : "2-3",
									right : "1-4"
								},
								left : {
									bottom : "2-1",
									top : "3-4"
								}
							};
							var o = obj[strDirect], arr = [];
							for (name in o) {
								arr.push(name);
							}
							if (funJudge(arr[0]) || !funJudge(arr[1])) {
								align = o[arr[0]];
							} else {
								align = o[arr[1]];
							}
						}
					})();
				}
			}

			// 已确定的尖角
			var strNewDirect = funDirect(align), strFirst = align.split("-")[0];
			if (this.s.sharpAngle) {
				// 创建尖角
				this.createSharp(strNewDirect);
				cor_w = this.corner.width(), cor_h = this.corner.height();
			}

			// 确定left, top值
			if (this.s.hoverFollow) {
				// 如果鼠标跟随
				if (this.s.hoverFollow === "x") {
					// 仅水平方向跟随
					tar_l = tri_l + off_x;
					if (strFirst === "1" || strFirst === "8"
							|| strFirst === "4") {
						// 最左
						tar_l = tri_l - (tar_w - tri_w) / 2 + off_x;
					} else {
						// 右侧
						tar_l = tri_l - (tar_w - tri_w) + off_x;
					}

					// 这是垂直位置，固定不动
					if (strFirst === "1" || strFirst === "5"
							|| strFirst === "2") {
						tar_t = tri_t - off_y - tar_h - cor_h;
						// 尖角
						cor_t = tri_t - cor_h - off_y - 1;

					} else {
						// 下方
						tar_t = tri_t + tri_h + off_y + cor_h;
						cor_t = tri_t + tri_h + off_y + 1;
					}
					cor_l = pos.left - (cor_w - tri_w) / 2;
				} else if (this.s.hoverFollow === "y") {
					// 仅垂直方向跟随
					if (strFirst === "1" || strFirst === "5"
							|| strFirst === "2") {
						// 顶部
						tar_t = tri_t - (tar_h - tri_h) / 2 + off_y;
					} else {
						// 底部
						tar_t = tri_t - (tar_h - tri_h) + off_y;
					}

					if (strFirst === "1" || strFirst === "8"
							|| strFirst === "4") {
						// 左侧
						tar_l = tri_l - tar_w - off_x - cor_w;
						cor_l = tri_l - cor_w - off_x - 1;
					} else {
						// 右侧
						tar_l = tri_l + tri_w - off_x + cor_w;
						cor_l = tri_l + tri_w + off_x + 1;
					}
					cor_t = pos.top - (cor_h - tri_h) / 2;
				} else {
					tar_l = tri_l + off_x;
					tar_t = tri_t + off_y;
				}

			} else {
				switch (strNewDirect) {
				case "top": {
					tar_t = tri_t - off_y - tar_h - cor_h;
					if (strFirst == "1") {
						tar_l = tri_l - off_x;
					} else if (strFirst === "5") {
						tar_l = tri_l - (tar_w - tri_w) / 2 - off_x;
					} else {
						tar_l = tri_l - (tar_w - tri_w) - off_x;
					}
					cor_t = tri_t - cor_h - off_y - 1;
					cor_l = tri_l - (cor_w - tri_w) / 2;
					break;
				}
				case "right": {
					tar_l = tri_l + tri_w + off_x + cor_w;
					if (strFirst == "2") {
						tar_t = tri_t + off_y;
					} else if (strFirst === "6") {
						tar_t = tri_t - (tar_h - tri_h) / 2 + off_y;
					} else {
						tar_t = tri_t - (tar_h - tri_h) + off_y;
					}
					cor_l = tri_l + tri_w + off_x + 1;
					cor_t = tri_t - (cor_h - tri_h) / 2;
					break;
				}
				case "bottom": {
					tar_t = tri_t + tri_h + off_y + cor_h;
					if (strFirst == "4") {
						tar_l = tri_l + off_x;
					} else if (strFirst === "7") {
						tar_l = tri_l - (tar_w - tri_w) / 2 + off_x;
					} else {
						tar_l = tri_l - (tar_w - tri_w) + off_x;
					}
					cor_t = tri_t + tri_h + off_y + 1;
					cor_l = tri_l - (cor_w - tri_w) / 2;
					break;
				}
				case "left": {
					tar_l = tri_l - tar_w - off_x - cor_w;
					if (strFirst == "2") {
						tar_t = tri_t - off_y;
					} else if (strFirst === "6") {
						tar_t = tri_t - (tar_w - tri_w) / 2 - off_y;
					} else {
						tar_t = tri_t - (tar_h - tri_h) - off_y;
					}
					cor_l = tar_l + cor_w;
					cor_t = tri_t - (tar_w - cor_w) / 2;
					break;
				}
				}
			}
			// 尖角的显示
			if (cor_h && cor_w && this.corner) {
				this.corner.css({
					left : cor_l,
					top : cor_t,
					zIndex : this.s.zIndex + 1
				});
			}
			// 浮动框显示
			this.target.css({
				position : "absolute",
				left : tar_l,
				top : tar_t,
				zIndex : this.s.zIndex
			});
			return this;
		},
		createSharp : function(dir) {
			var bgColor, bdColor, color1 = "", color2 = "";
			var objReverse = {
				left : "right",
				right : "left",
				bottom : "top",
				top : "bottom"
			}, dirReverse = objReverse[dir] || "top";

			if (this.target) {
				bgColor = this.target.css("background-color");
				if (parseInt(this.target.css("border-" + dirReverse + "-width")) > 0) {
					bdColor = this.target
							.css("border-" + dirReverse + "-color");
				}

				if (bdColor && bdColor !== "transparent") {
					color1 = 'style="color:' + bdColor + ';"';
				} else {
					color1 = 'style="display:none;"';
				}
				if (bgColor && bgColor !== "transparent") {
					color2 = 'style="color:' + bgColor + ';"';
				} else {
					color2 = 'style="display:none;"';
				}
			}

			var html = '<div id="floatCorner_' + dir
					+ '" class="float_corner float_corner_' + dir + '">'
					+ '<span class="corner corner_1" ' + color1 + '>◆</span>'
					+ '<span class="corner corner_2" ' + color2 + '>◆</span>'
					+ '</div>';
			if (!$("#floatCorner_" + dir).size()) {
				$("body").append($(html));
			}
			this.corner = $("#floatCorner_" + dir);
			return this;
		},
		displayDetect : function() {
			// 显示与否检测与触发
			if (!this.flagDisplay && this.display) {
				this.targetHide();
				this.timerHold = null;
			}
			return this;
		},
		targetShow : function() {
			o.cornerClear();
			this.display = true;
			this.container().setWidth().position();
			this.target.show();
			if ($.isFunction(this.s.showCall)) {
				this.s.showCall.call(this.trigger, this.target);
			}
			return this;
		},
		targetHide : function() {
			this.display = false;
			this.targetClear();
			this.cornerClear();
			if ($.isFunction(this.s.hideCall)) {
				this.s.hideCall.call(this.trigger);
			}
			this.target = null;
			this.trigger = null;
			this.s = {};
			this.targetProtect = false;
			return this;
		},
		targetClear : function() {
			if (this.target) {
				if (this.target.data("width")) {
					this.target.removeData("width").removeData("height");
				}
				if (this.targetProtect) {
					// 保护孩子
					this.target.children().hide().appendTo($("body"));
				}
				this.target.unbind().hide();
			}
		},
		cornerClear : function() {
			if (this.corner) {
				// 使用remove避免潜在的尖角颜色冲突问题
				this.corner.remove();
			}
		},
		getTarget : function(key) {
			var newTarget = [], index = 0, targetNow = targetObj
					.get(o.s.targetField);
			if (o.s.selectType === "value") {
				for ( var i = 0; i < targetNow.length; i++) {
					if (targetNow[i].value.indexOf(key) == 0) {
						newTarget[index++] = targetNow[i];
					}
				}
			} else {
				for ( var i = 0; i < targetNow.length; i++) {
					if (targetNow[i].key.indexOf(key) == 0) {
						newTarget[index++] = targetNow[i];
					}
				}
			}
			o.s.target = newTarget;
		},
		target : null,
		trigger : null,
		s : {},
		cacheData : {},
		targetProtect : false
	};

	$.powerFloat = {};
	$.powerFloat.hide = function() {
		o.targetHide();
	};

	var defaults = {
		width : "auto", // 可选参数：inherit，数值(px)
		offsets : {
			x : 0,
			y : 0
		},
		zIndex : 999,

		eventType : "hover", // 事件类型，其他可选参数有：click, focus

		showDelay : 0, // 鼠标hover显示延迟
		hideDelay : 0, // 鼠标移出隐藏延时

		hoverHold : true,
		hoverFollow : false, // true或是关键字x, y

		targetMode : "common", // 浮动层的类型，其他可选参数有：ajax, list, remind
		target : null, // target对象获取来源，优先获取，如果为null，则从targetAttr中获取。
		targetAttr : "rel", // target对象获取来源，当targetMode为list时无效

		container : null, // 转载target的容器，可以使用"plugin"关键字，则表示使用插件自带容器类型
		reverseSharp : false, // 是否反向小三角的显示，默认ajax,
		// remind是显示三角的，其他如list和自定义形式是不显示的

		position : "4-1", // trigger-target
		edgeAdjust : true, // 边缘位置自动调整

		showCall : $.noop,
		hideCall : $.noop

	};
})(jQuery);