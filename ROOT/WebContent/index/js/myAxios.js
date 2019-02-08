$(document).ready(function () {
	//刷新按钮

	
	//关于歌单类表单的提交
	$('.formAboutList').submit(function() {
		//todo
		let id = $('#userId').text();
		//alert(id);
		let url = 'InitMusicListServlet';
	
		

		
		axios.get(url+'?userId='+id).then(function (response) {
			console.log(response);
		    console.log(response.data);
		    let data = response.data;
		    $('#myListTable tbody').empty();
		    let title = `<tr>
							<th class="col-md-9">我的歌单</th>
							<th class="col-md-3"></th>
						</tr>`;
		    $('#myListTable tbody').append(title);
		    for (let i = 0; i < data.length; i++) {
                let content = `
           <tr desc="`+data[i].listDesc+`"  >
				<td listId="`+data[i].listId+`" class="showMusics">`+data[i].listName+`</td>
				<td>
			<div class="icon-group" style="display: flex;flex-direction: row;justify-content: space-around;">
		<img  listId="`+data[i].listId+`" listName="`+data[i].listName+`" listDesc="`+data[i].listDesc+`" src="index/img/edit.png"
			class="editMusicList" alt="编辑" data-toggle="modal" data-target="#editMusicList" >
		<img  listId="`+data[i].listId+`" src="index/img/delete.png"
		class="deleteMusicList" alt="删除"  data-toggle="modal" data-target="#deleteMusicList" >
		</div>
			</td>
			</tr>
			<tr>
			<td>简介`+data[i].listDesc+`</td>
			</tr>
            `
                $('#myListTable tbody').append(content);
            }
		    
			alert("提交成功");

			$("#addMusicList button").trigger('click');
			$("#editMusicList button").trigger('click');
			$("#deleteMusicList button").trigger('click');
		    
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
		
		
		
		
	})
	
	$('.formAboutUser').submit(function() {
		alert("提交成功");
		$("#editUserInfo").trigger('click');
	})
	$('#collectMusic form').submit(function() {
		alert("提交成功");
		$("#collectMusic").trigger('click');
	})
	
	
	// 在线音乐搜索
    // 以下函数在点击在线搜索后更变主体中的歌曲清单
    // 同时将歌曲的 lrc url id 绑定到 tr标签中
    $('.navbar-left button').click(function (e) {
        console.log($('.navbar-left input').val());
        let url = 'https://api.mlwei.com/music/api/wy/?key=523077333&cache=1&type=so&id=' + $('.navbar-left input').val();
        axios.get(url)
            .then(function (response) {
            	let data = response.data.Body;
                // 首先要清空
                $('#musicTable tbody').empty();
                console.log(data);
                // 为table添加一个第一行 来规范table
                let title = `<tr id="musicTableTitle">
                <th class="col-md-5">歌曲名</th>
                <th class="col-md-3 "></th>
                <th class="col-md-2">艺术家</th>
                <th class="col-md-2">专辑</th>
            </tr>`
                $('#musicTable tbody').append(title);
                for (let i = 0; i < data.length; i++) {
                	console.log(data[i].title);
                    let content = `
                <tr class="oneResult">
                    	<th>`+data[i].title+`</th>
						<th>
							<div class="icon-group">
                            <img src="/ROOT/index/img/play.png"
                            alt="播放" class="playIcon" author="`+data[i].author+`" title1="`+data[i].title+`" id="`+data[i].id+`" url="`+data[i].url+`" lrc="`+data[i].lrc+`" pic="`+data[i].pic+`"  onclick="clickPlayIcon(this)" > 
								<img src="/ROOT/index/img/collect.png"
									alt="收藏" data-toggle="modal" data-target="#collectMusic" author="`+data[i].author+`" title1="`+data[i].title+`"  url="`+data[i].url+`" lrc="`+data[i].lrc+`" pic="`+data[i].pic+`" 
									name="collect"  onclick="collectOnlineMusic(this)"> 
							</div>
						</th>
						<th>`+ data[i].author + `</th>
						<th>`+ data[i].author + `</th>
					<tr />
                `
                    $('#musicTable tbody').append(content);
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    	});
    	
    //	点击歌单后的删除按钮后触发删除事件 传值listId
	  $('.deleteList').click(function() {
		console.log($(this).attr('listId'));
		let  listId = $(this).attr('listId');
		axios.post(url,{"listId":listId}).then(function (response) {
		    console.log(response);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
		
		
	})
	
	//点击歌单返回音乐列表
	$('.showMusics').click(function() {
		let id = $(this).attr("listId");
		id=""+id;
		axios.get('ShowMusicsFromListServlet?listId='+id, {})
		 .then(function (response) {
		    console.log(response.data);
		    let data = response.data;
		    render2(data,id);
		 }).catch(function (error) {
		    console.log(error);
		 });
	})
	
	
	//点击本地乐库
	$('#searchLocal').click(function() {
		let url = '../LocalMusicServlet';
		axios.get(url,{value:"1"}).then(function (response) {
		    console.log(response.data);
		    let data = response.data;
		    render1(data);
		  })
		  .catch(function (error) {
		    console.log(error);
		  });
	 })
});

//点击收藏后出现的情况
function collectOnlineMusic(e) {
	  console.log(e);
	  console.log("这是歌曲title",e.getAttribute("title1"));
	  console.log("这是歌曲作者",e.getAttribute("author"));
	  console.log("这是歌曲id",e.getAttribute("id"));
	  console.log("这是歌曲url",e.getAttribute("url"));
	  console.log("这是歌曲lyc",e.getAttribute("lrc"));
	  console.log("这是歌曲pic",e.getAttribute("pic"));
	  $('#collectMusicName').text(e.getAttribute("title1"));
	  $('#collectMusicArt').text(e.getAttribute("author"));
	  
	  
	  $('#musicTitle').val(e.getAttribute("title1"));
	  $('#musicAuthor').val(e.getAttribute("author"));
	  $('#musicPath').val(e.getAttribute("url"));
	  
	  
	  
}



// 点击播放按钮后出现的情况
function clickPlayIcon(e){
	  console.log(e);
	  console.log("这是歌曲title",e.getAttribute("title1"));
	  console.log("这是歌曲id",e.getAttribute("id"));
	  console.log("这是歌曲url",e.getAttribute("url"));
	  console.log("这是歌曲lyc",e.getAttribute("lrc"));
	  console.log("这是歌曲pic",e.getAttribute("pic"));
	  //	动态添加图片
	  $('.bottom-center img').attr('src',e.getAttribute("pic"));
	  $('#left-wrap img').attr('src',e.getAttribute("pic"));
	  //	动态添加歌词
	  $('#bottomTitle').text(e.getAttribute("title1")+'-'+e.getAttribute("author"));
	  //	动态改变audio的值
	  $('audio').attr('src',e.getAttribute("url"));
	  //	自动点击暂停按钮
	  $('._btn.btn-play').trigger('click');
	  //	找歌词
	  axios.get(e.getAttribute("lrc"))
	  .then(function (response) {
		  $('#lrc').empty();
		  console.log(response.data.split('\n'));
		  let ircArr = response.data.split("\n");
	        for(let i = 0 ; i < ircArr.length;i++){
	            let content = ' <li class="list-group-item filter"><p>'+ircArr[i]+'</p></li>'
	            $('#lrc').append(content);
	        }
	  })
	  .catch(function (error) {
	    console.log(error);
	  });
 }



//这是生成搜索出来的歌的函数 需要传值
//在mid-wrap中显示
//参数为  一个json数组
//收藏
function render1(data) {
	// 首先要清空
    $('#musicTable tbody').empty();
    console.log(data);
    // 为table添加一个第一行 来规范table
    let title = `<tr id="musicTableTitle">
    <th class="col-md-5">歌曲名</th>
    <th class="col-md-3 "></th>
    <th class="col-md-2">艺术家</th>
    <th class="col-md-2">专辑</th>
</tr>`
    $('#musicTable tbody').append(title);
    for (let i = 0; i < data.length; i++) {
        let content = `
    <tr class="oneResult">
        	<th>`+data[i].musicName+`</th>
			<th>
				<div class="icon-group">
                <img src="/ROOT/index/img/play.png"
                alt="播放" class="playIcon" author="`+data[i].musicArt+`" title="`+data[i].musicName+`" id="`+data[i].musicId+`" url="`+data[i].musicPath+`" lrc="`+data[i].lrc+`" pic="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1547751337321&di=c12012c329c4d0b94b13c942b654850c&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170804%2F642c96a8df974326971da42818ffd7bc_th.jpg"  onclick="clickPlayIcon(this)" > 
					<img src="/ROOT/index/img/collect.png"
						alt="收藏"  data-toggle="modal" data-target="#collectMusic"
						name="collect" > 
				</div>
			</th>
			<th>`+ data[i].musicArt + `</th>
			<th>`+ data[i].musicArt + `</th>
		<tr />
    `
        $('#musicTable tbody').append(content);
    }
}

//这是生成搜索出来的歌的函数 需要传值
//在mid-wrap中显示
//参数为  一个json数组
//收藏
function render2(data,id) {
	// 首先要清空
  $('#musicTable tbody').empty();
  console.log(data);
  // 为table添加一个第一行 来规范table
  let title = `<tr id="musicTableTitle">
  <th class="col-md-5">歌曲名</th>
  <th class="col-md-3 "></th>
  <th class="col-md-2">艺术家</th>
  <th class="col-md-2">专辑</th>
</tr>`
  $('#musicTable tbody').append(title);
  for (let i = 0; i < data.length; i++) {
      let content = `
  <tr class="oneResult">
      	<th>`+data[i].musicName+`</th>
			<th>
				<div class="icon-group">
              <img src="/ROOT/index/img/play.png"
              alt="播放" class="playIcon" author="`+data[i].musicArt+`" title="`+data[i].musicName+`" id="`+data[i].musicId+`" url="`+data[i].musicPath+`" lrc="`+data[i].lrc+`"   onclick="clickPlayIcon(this)"  pic="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1547751337321&di=c12012c329c4d0b94b13c942b654850c&imgtype=0&src=http%3A%2F%2Fimg.mp.sohu.com%2Fq_mini%2Cc_zoom%2Cw_640%2Fupload%2F20170804%2F642c96a8df974326971da42818ffd7bc_th.jpg" > 
					<img src="/ROOT/index/img/collected.png" id="`+data[i].musicId+`"
						alt="已收藏"  class="cancelCollect" musicId = "`+data[i].musicId+`" listId = "`+id+`"
						name="collected" onclick="cancelCollect(this)"> 
				</div>
			</th>
			<th>`+ data[i].musicArt + `</th>
			<th>`+ data[i].musicArt + `</th>
		<tr />
  `
      $('#musicTable tbody').append(content);
  }
}

function cancelCollect(e) {
	/*alert(1);
	//alert(e.getAttribute("musicId"));
	alert(e.getAttribute("listId"));*/
	let musicId = e.getAttribute("musicId");
	let listId = e.getAttribute("listId");
	let url = 'CancelCollectMusicServlet';
	axios.get(url+'?musicId='+musicId+'&listId='+listId)
	.then(function (response) {
		console.log(response);
	  })
	  .catch(function (error) {
	    console.log(error);
	  });
	let that = $('#'+musicId+'');
	console.log( $('#'+musicId+''));
	alert('已将该歌曲从歌单中删除');
	that.parents('.oneResult').hide();
	
	
}

function shuaxin() {
	console.log(response);
    console.log(response.data);
    let data = response.data;
    $('#myListTable tbody').empty();
    let title = `<tr>
					<th class="col-md-9">我的歌单</th>
					<th class="col-md-3"></th>
				</tr>`;
    $('#myListTable tbody').append(title);
    for (let i = 0; i < data.length; i++) {
        let content = `
   <tr desc="`+data[i].listDesc+`"  >
		<td listId="`+data[i].listId+`" class="showMusics">`+data[i].listName+`</td>
		<td>
	<div class="icon-group" style="display: flex;flex-direction: row;justify-content: space-around;">
<img  listId="`+data[i].listId+`" onclick="shuaxin()" listName="`+data[i].listName+`" listDesc="`+data[i].listDesc+`" src="index/img/edit.png"
	class="editMusicList" alt="编辑" data-toggle="modal" data-target="#editMusicList" >
<img  listId="`+data[i].listId+`" src="index/img/delete.png"
class="deleteMusicList" alt="删除"  data-toggle="modal" data-target="#deleteMusicList" >
</div>
	</td>
	</tr>
	<tr>
	<td>简介`+data[i].listDesc+`</td>
	</tr>
    `
        $('#myListTable tbody').append(content);
    }
}