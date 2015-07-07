var cssIsLoaded=false;
var icon_path="images/ico/";

function MenuTree(name,type){
  this.name=name;
  this.type=type;
  this.rootNode=null;
  this.selectedNode=null;
  this.selectedTagObj=null;
  this.maxLevel=6;
}

function Node(id,parentId,name,level,queryFn,addFn,modifyFn,deleteFn,url,queryEnable,addEnable,modifyEnable,deleteEnable,seq,target,fn,opend,nohref){
  this.seq=seq;
  this.id=id;
  this.parentId=parentId;
  this.name=name!=""?name:"NoValue";
  this.level=level;
  this.url=url!=""?url:"";
  this.target=target!=""?target:"";
  this.childs=[];
  this.parent=null;
  
  this.childCount=0;
  this.start_open=typeof opend=="boolean"?opend:true;
  this.hasChildNoHref=typeof nohref=="boolean"?nohref:true;
  this.fn=typeof fn=="function"?fn:null;
  
  this.queryFn=typeof queryFn=="boolean"?queryFn:true;
  this.addFn=typeof addFn=="boolean"?addFn:true;
  this.modifyFn=typeof modifyFn=="boolean"?modifyFn:true;
  this.deleteFn=typeof deleteFn=="boolean"?deleteFn:true;
  
  this.queryEnable=typeof queryEnable=="boolean"?queryEnable:true;
  this.addEnable=typeof addEnable=="boolean"?addEnable:true;
  this.modifyEnable=typeof modifyEnable=="boolean"?modifyEnable:true;
  this.deleteEnable=typeof deleteEnable=="boolean"?deleteEnable:true;
}

MenuTree.prototype.addNode=function(id,parentId,name,level,queryFn,addFn,modifyFn,deleteFn,url,queryEnable,addEnable,modifyEnable,deleteEnable,seq,target,fn,opend,nohref){
  var node=new Node(id,parentId,name,level,queryFn,addFn,modifyFn,deleteFn,url,queryEnable,addEnable,modifyEnable,deleteEnable,seq,target,fn,opend,nohref);
  if(this.rootNode==null){
  	this.rootNode=node;
	return this.rootNode;
  }
  var pNode = node.findParent(this.rootNode);
  if(pNode!=null){
	
	node.parent=pNode;
	pNode.childs.push(node);
	pNode.childCount++;
  }
  else{
    node.parent=this.rootNode;
	this.rootNode.childs.push(node);
	this.rootNode.childCount++;
  }
  return node;
}

MenuTree.prototype.isNodeExist=function(node){
  if(node==null) return false;
  return node.isNodeExist(this.rootNode);
}

MenuTree.prototype.getNode=function(id){
  return this.rootNode.getNode(id);
}

MenuTree.prototype.findParentNode=function(node){
  return node.findParent(this.rootNode);
}

MenuTree.prototype.createMenu=function(obj){ 
  if(this.rootNode==null) return;
  this.rootNode.createMenu(obj,this);
  return;
}

MenuTree.prototype.moveAllToTree=function(menuTree){  
  var obj;
  var treeObj;
  
  if(this.type=="right"){
    this.rootNode.childCount=0;
    this.rootNode.childs.length=0;
    
    obj=$(this.name);
    treeObj=this;
  }
  else{
  	menuTree.rootNode=this.rootNode.clone(true);
  	
  	obj=$(menuTree.name);
  	treeObj=menuTree;
  }
  
  
  for(var i=obj.children.length-1; i>=0; i--){
    obj.children[i].removeNode(true);
  }
  
  treeObj.createMenu(treeObj.name,treeObj);
	
  return true;
} 

MenuTree.prototype.moveToTree=function(menuTree){ 
  if(this.selectedNode==null) return false;
  if(this.selectedNode.id==this.rootNode.id) return false;
  if(this.selectedNode.childCount>0) return false;
  
  if(this.type=="right"){
    var node=this.selectedNode;
    
    this.removeNode(this.selectedNode);
  
	this.selectedNode=null;
	this.selectedTagObj=null;
	
	while((node=node.parent)!=null){
	  if(node.id==this.rootNode.id) break;
	  if(node.childCount!=0) break;
	  else this.removeNode(node);
	}
	
	var obj=$(this.name);
    for(var i=obj.children.length-1; i>=0; i--){
      obj.children[i].removeNode(true);
    }

    this.createMenu(this.name,this);
	
	return true;
  }
  
  
  var node=this.selectedNode;
  var temp1=null;
  var subRootNode=null;
  var targetTreeNode=null;
  
  while((targetTreeNode=menuTree.getNode(node.id))==null){
    
	temp1=node.clone(false);
	temp1.childCount=0;
	temp1.childs.length=0;
	
	if(subRootNode!=null){
	  subRootNode.parent=temp1;
	  temp1.childs[0]=subRootNode;
	  temp1.childs.length=1;
	  temp1.childCount=1;
	  
	}
	
	subRootNode=temp1;
	
	node=node.parent;
	
	if(node==null) return false;
  }
  
  
  if(node.id==this.selectedNode.id) return false;
  
  this.selectedNode=null;
  this.selectedTagObj=null;
  
  var index=targetTreeNode.childCount;

  for(var i=0; i<targetTreeNode.childCount; i++){
	if(targetTreeNode.childs[i].id>subRootNode.id){
	  for(var j=targetTreeNode.childCount; j>i; j--){
		targetTreeNode.childs[j]=targetTreeNode.childs[j-1];
	  }
	  index=i;
	  break;
	}
  }
  
  subRootNode.parent=targetTreeNode;
  targetTreeNode.childs[index]=subRootNode;
  targetTreeNode.childCount++;
  
  var iNode=node;
  
  while(iNode!=null&&iNode.id!=this.rootNode.id){
	if(iNode.childCount==0) this.removeNode(iNode);
	else break;
	iNode=iNode.parent;
  }
  

  
  var obj=$(this.name);
  for(var i=obj.children.length-1; i>=0; i--){
    obj.children[i].removeNode(true);
  }
  
  obj=$(menuTree.name);
  for(var i=obj.children.length-1; i>=0; i--){
    obj.children[i].removeNode(true);
  }
  
  this.createMenu(this.name,this);
  menuTree.createMenu(menuTree.name,menuTree);
  
  return true;
}

MenuTree.prototype.toString=function(node){ 
  var str="";
  if(node.id!=this.rootNode.id){
    str="menuId:"+node.seq
  	  +",generateMenu:"+(node.queryFn?"Y":"N")
  	  +",addMenu:"+(node.addFn?"Y":"N")
  	  +",editMenu:"+(node.modifyFn?"Y":"N")
  	  +",deleteMenu:"+(node.deleteFn?"Y":"N")+";";
  }
  
  for(var i=0; i<node.childCount; i++){
    str+=this.toString(node.childs[i]);
  }
  return str;
}

MenuTree.prototype.removeNode=function(node){
  if(node==null) return false;
  if(node.parent==null||node.parent=="") return false;
  var pNode = node.parent;
  
  if(pNode.childCount==1||node.id==pNode.childs[pNode.childCount-1].id){
    pNode.childCount--;
	pNode.childs.length=pNode.childCount;
	return true;
  }
  
  for(var i=0; i<pNode.childCount; i++){
    if(pNode.childs[i].id==node.id){
	  for(var j=i; j<pNode.childCount-1; j++){
	    pNode.childs[j]=pNode.childs[j+1];
  	  }
	  pNode.childCount--;
	  pNode.childs.length=pNode.childCount;
	  
	  return true;
    }
  }
  return false;
}


Node.prototype.clone=function(isDeepClone){
  	var newNode=new Node(this.id,this.parentId,this.name,this.level,this.queryFn,this.addFn,this.modifyFn,this.deleteFn,this.url,this.queryEnable,this.addEnable,this.modifyEnable,this.deleteEnable,this.seq,this.target,this.fn,this.start_open,this.hasChildNoHref);
	newNode.childCount=0;
	//newNode.parent=this.parent;
	if(isDeepClone){
	  for(var i=0; i<this.childCount; i++){
	    newNode.childs[i]=this.childs[i].clone(isDeepClone);
	    newNode.childs[i].parent=newNode;
	  }
	  newNode.childCount=this.childCount;
	}
	return newNode;
}

Node.prototype.findParent=function(root){
	if(root.id == this.parentId) return root;
	var pNode;
	for(var i=0; i<root.childCount; i++){
  		pNode = this.findParent(root.childs[i]);
  		if(pNode != null) return pNode;
	}
	return null;
};

Node.prototype.isNodeExist=function(root){
  if(root.id == this.id) return true;
	var isExist = false;
	for(var i=0; i<root.childCount; i++){
  		isExist = this.isNodeExist(root.childs[i]);
  		if(isExist) break;
	}
	return isExist;
}


Node.prototype.getNode=function(id){
  var node=null;
  if(id == this.id) return this;
  for(var i=0; i<this.childCount; i++){
    node=this.childs[i].getNode(id);
    if(node!=null) return node;
  }
  return null;
}

Node.prototype.createMenu=function(obj,menuTree){
  var _this=this;
  var _obj=$(obj);
  if(!cssIsLoaded){
    addStyle(_obj);
    cssIsLoaded=true;
  }
  
  menuTree.selectedNode=null;
  menuTree.selectedTagObj=null;
  
  var _node=document.createElement("a");
  setHtml(_node,getImg("normal"));
  _node.style.cssText="cursor:hand";
  var textNode=document.createElement("a");
  setHtml(textNode,this.name);
  textNode.id="text";
  textNode.selected=false;
  textNode.style.cssText="font-size:13pt;cursor:hand";
  textNode.onclick=function(){
	if(this.selected){
	  menuTree.selectedNode=null;
	  this.selected=false;
	  this.style.border="";
	  this.style.backgroundColor="#FFFFFF";
	}
	else{
	  menuTree.selectedNode=_this;
	  if(menuTree.selectedTagObj!=null){
	    menuTree.selectedTagObj.style.border="";
		menuTree.selectedTagObj.style.backgroundColor="#FFFFFF";
		menuTree.selectedTagObj.selected=false;
	  }
	  menuTree.selectedTagObj=this;
	  this.style.border="1px ridge #cccccc";
	  this.style.backgroundColor="#faece9";
	  this.selected=true;
	}
	if(menuTree.type=="single") showNodeInfo(menuTree.selectedNode);
  };
  
  var titleDiv=document.createElement("div"); 
  titleDiv.style.cssText="padding:2px 0px 2px 0px;float:left;";
  titleDiv.appendChild(_node);
  titleDiv.appendChild(textNode);
  
  //alert(_obj.style.marginLeft+"_"+_obj.style.width);
  
  var closes=document.createElement("div");
  if(menuTree.type=="right") closes.style.cssText="border-bottom:1px solid #faece9;";
  closes.style.height="22px";
  closes.id=this.id;
  closes.parentId=this.parentId;
  closes.level=this.level;
  closes.appendChild(titleDiv);
  if(menuTree.type=="right"&&this.id!=menuTree.rootNode.id&&this.childCount==0){
    var div=document.createElement("span");
    div.style.cssText="width:220px;position:absolute;left:"+(menuTree.maxLevel*30+30)+"px;";
	
	if(this.queryEnable){
      var checkBox1=document.createElement("input");
      checkBox1.type="checkbox";
	  checkBox1.defaultChecked=_this.queryFn;
      checkBox1.onclick=function(){
	    _this.queryFn=this.checked;
      };
	  var queryFn=document.createTextNode("查询");
	
	  div.appendChild(checkBox1);
	  div.appendChild(queryFn);
	}
	
	if(this.addEnable){
	  var checkBox2=document.createElement("input");
      checkBox2.type="checkbox";
	  checkBox2.defaultChecked=_this.addFn;
      checkBox2.onclick=function(){
	    _this.addFn=this.checked;
      };
	  var addFn=document.createTextNode("新增");
	
	  div.appendChild(checkBox2);
	  div.appendChild(addFn);
	}
	
	if(this.modifyEnable){
	  var checkBox3=document.createElement("input");
      checkBox3.type="checkbox";
	  checkBox3.defaultChecked=_this.modifyFn;
      checkBox3.onclick=function(){
	    _this.modifyFn=this.checked;
      };
	  var modifyFn=document.createTextNode("修改");
	
	  div.appendChild(checkBox3);
	  div.appendChild(modifyFn);
	}
	
	if(this.deleteEnable){
	  var checkBox4=document.createElement("input");
      checkBox4.type="checkbox";
	  checkBox4.defaultChecked=_this.deleteFn;
      checkBox4.onclick=function(){
	    _this.deleteFn=this.checked;
      };
	  var deleteFn=document.createTextNode("删除");
	
	  div.appendChild(checkBox4);
	  div.appendChild(deleteFn);
	}
	
	closes.appendChild(div);
  }
  _obj.appendChild(closes);
  if(this.childCount>0){
    var outer=document.createElement("div");
	outer.style.cssText="margin-left:" + (this.level+1) * 20 + "px;width:auto";
	
    if(this.start_open){
      outer.style.display="block";
      setHtml(_node,getImg("open"));
    }else{
      outer.style.display="none";
      setHtml(_node,getImg("close"));
    }
	
    for(var i=0;i<this.childCount;i++){
      this.childs[i].createMenu(outer,menuTree);
    } 
    _obj.appendChild(outer);
  }
  else{
	if(this.fn){
		_node.onclick=fn;
	}
  }
  _node.onclick=function(){
    if(outer.style.display=="none"){
	  outer.style.display="block";
  	  setHtml(_node,getImg("open"));
	  _this.start_open=true;
    }else{
	  outer.style.display="none";
	  setHtml(_node,getImg("close"));
	  _this.start_open=false;
    }
    if(_this.fn){_this.fn();}
    return !_this.hasChildNoHref;
  };
  return _obj;
};
function $(id){
  return typeof id=="string"?document.getElementById(id):id;
}

function setText(obj,text){
  if(isIE()){
    obj.innerText=text;
  }else{
    obj.textContent=text;
  }
}

function setHtml(obj,text){
  obj.innerHTML=text;
}

function getImg(imgStr){
  return "<img style=\"vertical-align:bottom;border:0px;\" src=\"" + icon_path + imgStr + ".gif\" alt=\"\" />"
}

function isIE(){
  return navigator.userAgent.toLowerCase().indexOf("msie")>=0;
}

function addStyle(o){
  var cT="";
  cT+="#" + o.id + " A:link {TEXT-DECORATION: none;font-size:13px;color:#222;}";
  cT+="#" + o.id + " A:visited{TEXT-DECORATION: none;font-size:13px;color:#222;}";
  cT+="#" + o.id + " A:hover{TEXT-DECORATION: none;font-size:13px;color:#ccc;}";
  var STYLE=document.createElement('style');
  STYLE.setAttribute("type","text/css");
  STYLE.styleSheet&&(STYLE.styleSheet.cssText=cT)||STYLE.appendChild(document.createTextNode(cT));
  document.getElementsByTagName('head')[0].appendChild(STYLE);
}

var cookie={
    setCookie	: function(name, value, days) {var expires = "";if (days) {var d = new Date();d.setTime(d.getTime() + days * 24 * 60 * 60 * 1000);expires = "; expires=" + d.toGMTString();}document.cookie = name + "=" + value + expires + "; path=/";},
	getCookie	: function (name) {var re = new RegExp("(\;|^)[^;]*(" + name + ")\=([^;]*)(;|$)");var res = re.exec(document.cookie);return res != null ? res[3] : null;}
};

function showNodeInfo(selectedNode){
  if(selectedNode!=null){
	  var obj=document.getElementById("menuId");
	  if(obj!=null) obj.value=selectedNode.id;
	  obj=document.getElementById("menuName");
	  if(obj!=null) obj.value=selectedNode.name;
	  obj=document.getElementById("menuLevel");
	  if(obj!=null) obj.value=selectedNode.level;
	  obj=document.getElementById("menuParent");
	  if(obj!=null) obj.value=selectedNode.parentId;
	  obj=document.getElementById("menuLink");
	  if(obj!=null) obj.value=selectedNode.url;
	  
	  var fn="";
	  if(selectedNode.queryFn) fn += "查询 ";
	  if(selectedNode.addFn) fn += "新增 ";
	  if(selectedNode.modifyFn) fn += "修改 ";
	  if(selectedNode.deleteFn) fn += "删除 ";
	  obj=document.getElementById("menuFn");
	  if(obj!=null) obj.value=fn;
  }
  else{
	  var obj=document.getElementById("menuId");
	  if(obj!=null) obj.value="";
	  obj=document.getElementById("menuName");
	  if(obj!=null) obj.value="";
	  obj=document.getElementById("menuLevel");
	  if(obj!=null) obj.value="";
	  obj=document.getElementById("menuParent");
	  if(obj!=null) obj.value="";
	  obj=document.getElementById("menuLink");
	  if(obj!=null) obj.value="";
	  obj=document.getElementById("menuFn");
	  if(obj!=null) obj.value="";
  }
}