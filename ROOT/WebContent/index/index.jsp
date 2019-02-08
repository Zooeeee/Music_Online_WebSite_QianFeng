<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Document</title>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/index/css/index.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/index/css//bottom.css">
<script src="<%=request.getContextPath()%>/index/js/index.js"></script>
<script src="<%=request.getContextPath()%>/index/js/utils.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="<%=request.getContextPath()%>/index/js/myAxios.js"></script>
</head>
<body>
	<nav class="navbar navbar-default" id="top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar "></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"> <span
					class="glyphicon glyphicon-headphones"></span>&nbsp;&nbsp;云音乐
				</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<!--	这里是导航栏左部的查询表单-->
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<div class="form-group">
							<input type="text" class="form-control" style="width: 200px;"
								placeholder="在线搜索">
						</div>
						<button type="button" class="btn btn-success" value="搜索" >搜索</button>
					</div>
				</form>
				<button type="button" id="searchLocal" class="btn btn-default navbar-btn">本地乐库</button>

				<!--	这里是导航栏右部的管理下拉菜单组-->
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span
							class="glyphicon glyphicon-th-list" style="font-size: 15px;">歌单</span>
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a data-toggle="modal" data-target="#addMusicList">添加歌单</a></li>
							<li><a id="editLists" href="#">管理歌单</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span
							class="glyphicon glyphicon-envelope" style="font-size: 15px;">消息</span>
							<b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#">查看消息</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"
							style="font-size: 15px;">用户</span> <b class="caret"></b>
					</a>
						<ul class="dropdown-menu">
							<li><a href="#" data-toggle="modal"
								data-target="#editUserInfo">修改信息</a></li>
							<li><a href="#">退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--这是页面的主体部分-->
	<div class="container-fluid main">
		<div class="row">
			<!--这部分是展示图片和歌词-->
			<div class="col-md-3 main-height" id="left-wrap">
				<div class="row">
					<div class="col-md-12">
						<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1547751338196&di=e6f8a37a200ff4c26f5f757c2adea85e&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170804%2F642c96a8df974326971da42818ffd7bc_th.jpg"
							class="img-circle  center-block" id="cd" alt="">
					</div>
					<div class="col-md-12  pre-scrollable scroll max-height" id="ircScroll">
						<ul class="list-group " id="lrc">
							<li class="list-group-item filter">目前尚无歌词</li>
						</ul>
					</div>
				</div>
			</div>

			<!--这部分是展示搜索出来的或者歌单里的歌-->
			<div class="col-md-6  pre-scrollable  scroll  main-height max-height"
				id="mid-wrap">
				<!--歌单里的歌-->
				<table class="table table-hover " id="musicTable">
					<tr id="musicTableTitle">
						<th class="col-md-5">歌曲名</th>
						<th class="col-md-3 "></th>
						<th class="col-md-2">艺术家</th>
						<th class="col-md-2">专辑</th>
					</tr>
					<tr class="oneResult">
						<th></th>
						<th>
						</th>
						<th></th>
						<th></th>
					<tr />
				</table>

			</div>
			<!--这部分展示用户个人信息和歌单-->
			<div class="col-md-3 ain-height  " id="right-wrap">
				<div class="media">
					<div class="media-left">
						<a href="#"> <img class="media-object"
							src="${user.pic}"
							alt="头像" data-toggle="modal" data-target="#editHeadImg">
						</a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">${user.nick}</h4>
						<p id="userId" hidden="hidden">${user.userId}</p>
						<p>个性签名:&nbsp;${user.desc}</p>
					</div>
				</div>
				<!--这是歌单div-->
				<div class="col-md-12 pre-scrollable scroll max-height"
					style="margin-top: 30px;">
					<table class="table table-hover" id="myListTable">
						<tr>
							<th class="col-md-9">我的歌单</th>
							<th class="col-md-3"></th>
						</tr>
						<c:forEach items = "${musicLists}" var = "item">
							<tr desc="${item.listDesc}"  >
								<td listId="${item.listId}" class="showMusics">${item.listName}</td>
								<td>
									<div class="icon-group" style="display: flex;flex-direction: row;justify-content: space-around;">
										<img  listId="${item.listId}" listName="${item.listName }" listDesc="${item.listDesc }" src="<%=request.getContextPath()%>/index/img/edit.png"
									class="editMusicList" alt="编辑" data-toggle="modal" data-target="#editMusicList" >
									<img  listId="${item.listId}" src="<%=request.getContextPath()%>/index/img/delete.png"
									class="deleteMusicList" alt="删除"  data-toggle="modal" data-target="#deleteMusicList" >
									</div>
								</td>
							</tr>
							<tr>
								<td>简介:${item.listDesc}</td>
							</tr>
						</c:forEach>
						
					</table>
				</div>
			</div>
		</div>
		<!--row结束-->
	</div>
	<!--container结束-->
	<!--这是底部的播放状态栏-->
	<div class="bottom-wrap">
		<div class="bottom-left">
			<a href="javascript:;" title="上一首" class="_btn btn-prev">上一首</a> <a
				href="javascript:;" title="播放" class="_btn btn-play">播放</a> <a
				href="javascript:;" title="暂停" class="_btn btn-pause hide">暂停</a> 
				<a onclick="clickNext()" title="下一首" class="_btn btn-next">下一首</a>
		</div>
		<div class="bottom-right">
			<a href="javascript:;" title="循环列表" class="btn-repeat loop"
				id="play_mode"></a> <a href="javascript:;" title="音量"
				class="btn-volume"></a>
			<div class="volume-bar">
				<div class="con"></div>
				<div class="curr" id="curr-vol" style="width: 100%;">
					<div class="mark"></div>
				</div>
			</div>
			<div class="btn-quality">
				<i class="high"></i>
				<div class="hover-box">
					<a href="javascript:;" class="item-quality" data-val="super">高品<i></i></a>
					<a href="javascript:;" class="item-quality" data-val="high">标准<i></i></a>
					<a href="javascript:;" class="item-quality" data-val="normal">流畅<i></i></a>
				</div>
			</div>
		</div>
		<div class="bottom-center">
			<div class="cover">
				<img  src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1547751338196&di=e6f8a37a200ff4c26f5f757c2adea85e&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170804%2F642c96a8df974326971da42818ffd7bc_th.jpg">
			</div>
			<div class="con">
				<div class="info">
					<div class="btns">
						<a href="javascript:;" title="收藏" class="btn-collect disable"></a>
						<a href="javascript:;" title="下载"
							class="btn-download action_down disable"></a> <a
							href="javascript:;" title="转发" class="btn-share disable"></a>
					</div>
					<a href="javascript:;" target="_blank" class="title" id="bottomTitle">当前没有歌曲</a>
				</div>
				<div class="process-wrap">
					<div class="time">
						<span class="curr" id="curr">00:00</span>/<span class="duration" id="dura">00:00</span>
					</div>
					<div class="p-box" id="currbar">
						<div class="in" style="width: 515px;">
							<div class="load" style="width: 0%"></div>
							<div class="curr" id="currIcon" style="width: 0%">
								<a href="javascript:;" class="vernier"></a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--下方是音频播放audio标签-->
	<audio preload="auto"
		src="<%=request.getContextPath()%>/index/heisemaoyi.mp3 " id="audio">
	</audio>

	<!-- 模态框（Modal） -->
	<!--用来处理收藏歌单-->
	<div class="modal fade" id="collectMusic" tabindex="-1" role="dialog"
		aria-labelledby="collectMusic" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form target="trash" action="CollectionMusicServlet" method="post">
					<div class="modal-header">
						<h2 class="modal-title" id="collectMusicName">
							歌曲名
							</h4>
							<h4 class="modal-title" id="collectMusicArt">作者名</h4>
							<p>添加到以下哪个歌单？</p>
					</div>
					<div class="modal-body">
						<!--	单选按钮配合 上方h4的标题发送数据-->
						<!-- 	隐式输入框发送用户id -->
						<input type="hidden" name="userId" value="${user.userId }" />
						<input type="hidden" name="musicTitle" id="musicTitle" value="" />
						<input type="hidden" name="musicAuthor" id="musicAuthor" value="" />
						<input type="hidden" name="musicPath" id="musicPath" value="" />
						<input type="hidden" name="musicAlbum" id="musicPath" value="线上" />
						
						<c:forEach items = "${musicLists}" var = "item">
						<div class="checkbox">
							<label> <input type="radio" name="collectId" 
								value="${item.listId}" > ${item.listName} 
							</label>
						</div>
						</c:forEach>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<input type="submit" class="btn btn-primary" value="收藏" />
					</div>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<!--用来处理修改用户信息-->
	<div class="modal fade" id="editUserInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">修改个人信息</h4>
				</div>
				<div class="modal-body">
					<form  target="trash"  action="EditUserInfoServlet" method="post" class="form-horizontal formAboutUser">
						<input type="hidden" name="userId" value="${user.userId }">
						<div class="form-group">
							<label class="col-sm-2 control-label">登录账户:</label>
							<div class="col-sm-10">
								<p class="form-control-static">${user.userName}</p>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码:</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" name="userPwd"
									value="${user.userPwdString }" placeholder="密码">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">昵称</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="userNick" placeholder="昵称"
									value="${user.nick}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">性别</label>
							
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" checked="checked" value="男"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="inlineRadioOptions" value="女"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">个性签名</label>
							<div class="col-sm-10">
								<textarea class="form-control" name="userDesc" placeholder="个性签名">${user.desc }</textarea>
							</div>
						</div>


						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<button type="submit" class="btn btn-primary">提交更改</button>
						</div>
					</form>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<!--用来添加歌单-->
	<!--用来添加歌单-->
	<div class="modal fade" id="addMusicList" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">添加歌单</h4>
				</div>
				<form target="trash" class="form-horizontal formAboutList"  action="AddMusicListServlet"  method="post">
					<input type="hidden" name="listUid" value="${user.userId }" />
					<div class="modal-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">新建歌单名:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="listName" placeholder="新建歌单名">
							</div>
						</div>
					</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">详细信息:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="listDesc" placeholder="详细信息">
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<input type="submit" class="btn btn-primary" value="添加" />
					</div>
				</form>

			</div>
		</div>
	</div>
	<!--用来修改用户头像-->
	<div class="modal fade" id="editHeadImg" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">修改头像</h4>
				</div>
				<form  class="form-horizontal">
					<div class="modal-body">
						<div>
							<img src="<%=request.getContextPath()%>/index/img/meiqin.jpg"
								class="img-circle center-block"
								style="width: 100px; height: 100px;" />
							<p style="text-align: center;">当前头像</p>
						</div>
						<div class="form-group ">
							<label class="col-sm-2 control-label">上传新头像</label>
							<div class="col-sm-10">
								<input type="file" id="newHeadImgUrl"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<input type="submit" class="btn btn-primary" value="修改" />
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- 	修改歌单弹出窗口         -->
	<div class="modal fade" id="editMusicList" tabindex="-1" role="dialog">
    <div class="modal-dialog" >
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="myModalLabel">修改歌单歌单</h4>
        </div>
        <form target="trash"  class="form-horizontal formAboutList" action="UpdateMusicListServlet" method="post">
          <div class="modal-body">
          	<input type="hidden" id="editMusicListId" name="listId" value=""/>
              <div class="form-group">
                  <label  class="col-sm-2 control-label">修改歌单名:</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="listName" id="editMusicListName" placeholder="修改歌单名">
                  </div>
                </div>
                <div class="form-group">
                  <label  class="col-sm-2"  control-label">修改详情:</label>
                  <div class="col-sm-10">
                    <input type="text" id="editMusicListDesc" name="listDesc" class="form-control"  placeholder="修改歌单详情">
                  </div>
                </div>
          </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <input type="submit" class="btn btn-primary" value="添加"/>
        </div>
        </form>
      </div>
    </div>
  </div>
  
  <!-- 	删除歌单弹出窗口         -->
	<div class="modal fade " id="deleteMusicList" tabindex="-1" role="dialog">
    <div class="modal-dialog" >
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="myModalLabel">删除歌单歌单</h4>
        </div>
        <form target="trash" class="form-horizontal formAboutList" action="DeleteMusicListServlet" method="post">
        <div class="modal-footer">
          <input type="hidden" id="deleteMusicListId" name="listId" value="">
          <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          <input type="submit" class="btn btn-primary" value="删除"/>
        </div>
        </form>
      </div>
    </div>
  </div>
	
	
	<!-- 这是一个隐藏的iframe 用来防止表单跳转 -->
	<iframe name="trash" hidden src = ""></iframe>

</body>


</html>