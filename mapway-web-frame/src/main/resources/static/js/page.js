String.prototype.trim = function() {
	return this.replace(/(^\s+)|(\s+$)/g, '')
}
// 显示消息
function showMessage(msg) {
	var w = window.parent;
	while (w.parent != w) {
		w = w.parent;
	}
	var data = {
		msgtype : "msg",
		msg : msg
	}
	w.postMessage(data, "*");
}
/**
 * 根据路径构造一个树状对象
 */

function TreeItem() {
	this.data = undefined;
	this.children = new Array();
	this.name = "";
}
TreeItem.prototype.toHTML = function() {

	var html = "";
	var css = this.children.length > 0 ? "node_path" : "node_leaf";
	if (this.data != undefined) {
		html = "<div>";

		if (this.data.icon != null && this.data.icon.length > 0) {
			html += "<img style='margin-right:5px;' align='middle' width='64px' height='42px' src='" + this.data.icon
					+ "' />";
		}

		html += "<span url='" + this.data.url + "' class='item_clickable "
				+ css + "'>" + this.data.name + "</span>";
	} else {
		html = "<div><span  class='item_unclickable " + css + " '>" + this.name
				+ "</span>";
	}
	for (var j = 0; j < this.children.length; j++) {
		var subhtml = this.children[j].toHTML();
		html += subhtml;
	}
	html += "</div>";
	return html;

}

function TreeObject() {
	this.items = [];
	this.root = new TreeItem();
}

/**
 * / -->ABC /ABC ------>DEF
 * 
 * @param items
 */
TreeObject.prototype.setItems = function(items) {
	this.items = items;

	for (var i = 0; i < items.length; i++) {
		var item = items[i];
		var obj = this.findPath(item.path);

		var ti = new TreeItem();
		ti.name = item.name;
		ti.data = item;

		obj.children.push(ti);

	}
}
TreeObject.prototype.toHTML = function() {
	var html = "";
	for (var i = 0; i < this.root.children.length; i++) {
		var node = this.root.children[i];
		html += node.toHTML();
	}
	return html;
}

/**
 * / ===> [2] /ABC====>[2] /ABC/====>[3] ABC =====>[1]
 * 
 * @param path
 * @returns {TreeItem}
 */
TreeObject.prototype.findPath = function(path) {
	if (path == undefined) {
		path = "/";
	}
	path = path.trim();
	if (path == "") {
		path = "/";
	}

	var obj = this.root;

	// /ABC
	var segs = path.split("/");

	// remove last empty node
	if (segs[segs.length - 1] == "") {
		segs.splice(segs.length - 1, 1);
	}
	// /ABC ["",ABC]
	obj = this.root;
	for (var i = 0; i < segs.length; i++) {
		var seg = segs[i];
		var find = false;
		for (var j = 0; j < obj.children.length; j++) {
			if (obj.children[j].name == seg) {
				obj = obj.children[j];
				find = true;
				break;
			}
		}

		if (find == false) {
			var item = new TreeItem();
			item.name = seg;
			obj.children.push(item);
			obj = item;
		} else {
			continue;
		}
	}
	return obj;
}

/**
 * 关于界面的一些函数
 */

function Page(rootid) {
	var _this = this;
	var page = "<div id='page_top' class='page_top'>"
			+ "<table width='100%'>"
			+ "	<tr>"
			+ "		<td  id='page_menu'></td>"
			+ "		<td><div id='page_title'>泛能云平台</div><div id='page_msg'></div></td>"
			+ "		<td><div id='page_exit'></div></td>"
			+ "	</tr>"
			+ "</table>"
			+ "</div>"
			+ "<iframe id='frame_body' frameborder='no' border='0' marginwidth='0' marginheight='0' width='100%'></iframe>";
	$("#" + rootid).html(page);

	var btnMenu = $("<div class='btn_menu_button'></div>");
	btnMenu.click(function() {
		_this.toogleMenu();
	});
	$("#page_menu").append(btnMenu);

	this.mainMenu = $("<div class='main_menu_box'></div>");
	_this.buildMenu();
	$(document.body).append(this.mainMenu);
	$(window).resize(function() {
		_this._resize()
	});
	$(window).resize();

	// 处理消息
	window.addEventListener('message', function(e) {
		var d = e.data;
		console.log(e);
		if (d.msgtype == "msg") {
			_this.setMessage(d.msg);
		}
	}, false);
}
Page.prototype.setMessage = function(msg) {
	$("#page_msg").text(msg);
	// alert(msg);
}
Page.prototype.hideMainMenu = function() {
	var w = this.mainMenu.width();
	w = (-w) + "px";
	this.mainMenu.animate({
		'left' : w
	}, 200);
}
Page.prototype.toogleMenu = function() {
	var left = parseInt(this.mainMenu.css('left'));
	if (left > -3) {
		this.hideMainMenu();

	} else
		this.mainMenu.animate({
			left : '0px'
		}, 200);
}
Page.prototype.buildMenu = function(data) {
	var _this = this;
	// 初始化页面主菜单
	S.mainMenu({}, function(data) {
		if (data.code != 0) {
			_this.mainMenu.text(data.msg);
		} else {
			var html = "";
			var tree = new TreeObject();
			tree.setItems(data.menus);
			_this.mainMenu.html(tree.toHTML());
			$(".item_clickable", _this.mainMenu).click(function() {
				_this.naviTo($(this).attr("url"), $(this).text());
			});
		}
	});
}
Page.prototype.naviTo = function(url, title) {
	this.hideMainMenu();
	this.setContentUrl(url);
	this.setTitle("泛能云-" + title);

}
Page.prototype._resize = function() {
	var _this = this;
	var top_height = $("#page_top").outerHeight();
	var window_height = $(window).height();
	var h = (window_height - top_height - 4);
	$('#frame_body').css("height", h + "px");
	this.mainMenu.css("height", (h + 4) + "px");

}
Page.prototype.setTitle = function(title) {
	$("#page_title").text(title);
}
Page.prototype.setContentUrl = function(url) {
	$("#frame_body").attr("src", url);
}

Page.prototype.setUser = function(user) {
	var _this = this;
	this.user = user;
	var o = $("#page_exit");

	var u;

	u = $("<a href='javascript:void(0)'>" + user.NAME + "</a>");
	u.click(function() {
		_this.setContentUrl("user/info.jsp");
	});
	o.append(u);
	o.append(" | ")
	var exit = $("<a href='javascript:void(0)'>退出</a>");
	exit.click(function() {
		S.logout(_this.getToken(), function(data) {
			if (data.code != 0) {
				alert(data.msg);
			} else {
				$.removeCookie("token");
				window.location = "index.jsp";
			}
		});
	});
	o.append(exit);

}
Page.prototype.getToken = function() {
	return this.user.TOKEN;
}