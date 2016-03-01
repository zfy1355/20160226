<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/demo.css" type="text/css">
	<link rel="stylesheet" href="../css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="../js/jquery.ztree.core-3.5.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false,
				showLine: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeExpand: beforeExpand,
				onExpand: onExpand,
				onClick: onClick
			}
		};
		
		function createNodes(maxNodesNumInLevel, maxLevel, curLevel, curPId) {
			if (maxNodesNumInLevel<5) {
				maxNodesNumInLevel = 5;
			}
			var nodes = [], num = 0;
			while(num<3) {
				num = parseInt(Math.random()*1024)%maxNodesNumInLevel+1;
			}
			 for (var i=0; i<num; i++) {
				var id = curPId ? curPId + "-" + i : "" + i, isParent = (parseInt(Math.random()*9999)%3!=0),
				node = {id: id, pId : curPId, name : "N" + id};
				nodes.push(node);
				/* if (isParent && curLevel<maxLevel) {
					nodes = nodes.concat(createNodes(maxNodesNumInLevel, maxLevel, curLevel+1, id));
				} */
			} 
			return nodes;
		}
		var zNodes =createNodes(5, 5, 0);

		var curExpandNode = null;
		function beforeExpand(treeId, treeNode) {
			var pNode = curExpandNode ? curExpandNode.getParentNode():null;
			var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
				if (treeNode !== treeNodeP.children[i]) {
					zTree.expandNode(treeNodeP.children[i], false);
				}
			}
			while (pNode) {
				if (pNode === treeNode) {
					break;
				}
				pNode = pNode.getParentNode();
			}
			if (!pNode) {
				singlePath(treeNode);
			}

		}
		function singlePath(newNode) {
			if (newNode === curExpandNode) return;
			if (curExpandNode && curExpandNode.open==true) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				if (newNode.parentTId === curExpandNode.parentTId) {
					zTree.expandNode(curExpandNode, false);
				} else {
					var newParents = [];
					while (newNode) {
						newNode = newNode.getParentNode();
						if (newNode === curExpandNode) {
							newParents = null;
							break;
						} else if (newNode) {
							newParents.push(newNode);
						}
					}
					if (newParents!=null) {
						var oldNode = curExpandNode;
						var oldParents = [];
						while (oldNode) {
							oldNode = oldNode.getParentNode();
							if (oldNode) {
								oldParents.push(oldNode);
							}
						}
						if (newParents.length>0) {
							zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
						} else {
							zTree.expandNode(oldParents[oldParents.length-1], false);
						}
					}
				}
			}
			curExpandNode = newNode;
		}

		function onExpand(event, treeId, treeNode) {
			curExpandNode = treeNode;
		}

		function onClick(e,treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.expandNode(treeNode, null, null, null, true);
		}

		$(document).ready(function(){
			$.ajax({  
			    url: "/menu/getMenus.html",  
			    success: function(data){  
			       alert(data);
			    }     
			});  
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</SCRIPT>
	<style type="text/css">
		.ztree li button.switch {visibility:hidden; width:1px;}
		.ztree li button.switch.roots_docu {visibility:visible; width:16px;}
		.ztree li button.switch.center_docu {visibility:visible; width:16px;}
		.ztree li button.switch.bottom_docu {visibility:visible; width:16px;}
	</style>
<title>Insert title here</title>
</head>
<body>
<h1>保持展开单一路径</h1>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>实现方法说明</h2>
				<ul class="list">
				<li>此 Demo 是在 "单击展开/折叠节点" 基础上改造而来，树节点保持始终只展开一条路径。</li>
				<li class="highlight_red">利用 setting.callback.beforeExpand / onExpand 事件回调函数实现展开规则</li>
				</ul>
			</li>
		</ul>
	</div>
<c:forEach var="menu" items="${menus}" >
		<li>${menu.name }</li>
		<li>${menu.level }</li>
	</c:forEach>
	
</div>
</body>
</html>