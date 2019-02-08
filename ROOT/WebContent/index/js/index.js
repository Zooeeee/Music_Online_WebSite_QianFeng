$(document).ready(function(){ 
	  let arr = $('#right-wrap [alt="删除"]');
	  let arr2 = $('#right-wrap [alt="编辑"]');
	  arr.toggle();
	  arr2.toggle();
	   //开关歌单的删除按钮
	   $('#editLists').click(function(){
	      console.log($('#right-wrap img'));
	      let arr = $('#right-wrap [alt="删除"]');
	      let arr2 = $('#right-wrap [alt="编辑"]');
	      arr.toggle();
	      arr2.toggle();
	   });
	  
	
   /**
    * 下面两段代码控制播放按钮和暂停按钮的切换，
    * 并控制cd的旋转
    * 并控制歌曲的播放
    */
   let mp3 =  $('audio')[0];
   let time ;
   
   
   
   $('._btn.btn-play').click(function(e){
      $('._btn.btn-play').addClass('hide');  
      $('._btn.btn-pause').removeClass('hide');  //改变按钮图片
      $('#cd').addClass('rotation');   //控制cd旋转
      mp3.play();            //播放mp3
      console.log(mp3.duration);
      percentBar(); //	定时函数 一秒一次
      //console.log("滑动条:",$('#ircScroll').scrollTop()) ;
   });
   $('._btn.btn-pause').click(function(e){
      $('._btn.btn-pause').addClass('hide');
      $('._btn.btn-play').removeClass('hide');
      $('#cd').removeClass('rotation');
      mp3.pause();
      clearInterval(time);
   });


   //	获取MP3播放进度 修改进度条
   function percentBar(){ 
         time = setInterval(()=>{
         let curr = formatSeconds(mp3.currentTime);
         $('#curr').text(curr);
         $('#dura').text(formatSeconds(mp3.duration));
         let percent = (mp3.currentTime/mp3.duration)*100 +'%';
         console.log("当前歌曲进度",percent);
         $('#currIcon').css("width",percent);
     
      },200);
   }
   
   //控制音量
   $(".volume-bar").click(function(){
      //	获取点击音量条时的鼠标横坐标
      //	音量调左端为1134 右端为1234
      $('.volume-bar').mousedown(function (e) { 
         console.log(e.pageX);
         if(e.pageX <= 1234 && e.pageX>=1134){
         let percent = (e.pageX-1134)+'%';
         console.log("音量修改为",percent);
         $('#curr-vol').css("width",percent);
         let value = (e.pageX-1134)/100;
         mp3.volume = value; 
         //console.log(e.pageX);
         }
      });
   })

   //	获取歌曲进度的值 点击后调整歌曲进度
   //	左端为364  进度条长度 右端在878
   $("#currbar").click(function(e){
      $('#currbar').mousedown(function(e){
         console.log(e.pageX);
         if(e.pageX<=878&&e.pageX>=364){
            let percent = (e.pageX-364)/(878-374)*100+'%';
            let value = (e.pageX-364)/(878-374)*mp3.duration;
            console.log(percent);
            console.log("要去跳到第几秒",value);
            console.log(mp3.currentTime)
            $('#currIcon').css("width",percent);
            mp3.currentTime = value;
         }
      
      })
   })
   //	下面代码切换模仿模式图标
   $('#play_mode').click(function(){
      let that = $('#play_mode');
      if(that.hasClass('loop'))
         {
            that.removeClass('loop');
            that.addClass('random');
            that.attr("title","随机播放");
         }
         else if(that.hasClass('random'))
         {
            that.removeClass('random');
            that.addClass('single');
            that.attr("title","单曲循环");
         }
         else if(that.hasClass('single'))
         {
            that.removeClass('single');
            that.addClass('loop');
            that.attr("title","列表循环");
         }
   })
   
   // 	点击获取修改歌单的id和名字和详情
   $('.editMusicList').click(function() {
	   console.log("修改 歌单id"+$(this).attr("listId"));
	 $('#editMusicListId').val($(this).attr("listId"));
	 $('#editMusicListName').val($(this).attr("listName"));
	 $('#editMusicListDesc').val($(this).attr("listDesc"));
})
	 $('.deleteMusicList').click(function() {
		 console.log("删除 歌单id"+$(this).attr("listId"));
	 $('#deleteMusicListId').val($(this).attr("listId"));
	
})
   


   //	点击收藏获取data-id
   $(".postValue").click(function(){
      //收藏图标的
	  alert(1);
      let value1 = $(this).prev().attr("title");
      console.log(value1);
      let value1 = $(this).prev().attr("author");
      console.log(value2);
      $('#collectMusicName').text(value1);
      $('#collectMusicArt').text(value2);
   })
  

   //预览头像
   $('#newHeadImgUrl').bind("input propertychange",function(){
   
      let src = $('#newHeadImgUrl').val();
      
      $('#newHeadImgUrl').blur(function(){
         $('#previewImg').attr("src",src);
      })
      
   })

  
});

//点击下一曲
function clickNext(){
	console.log($('audio').attr("src"))
}
