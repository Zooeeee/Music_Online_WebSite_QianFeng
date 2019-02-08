/**
 * 
 * 下面是把秒转化成 xx:xx:xx格式的工具函数
 */

function formatSeconds(value) {
    let sec = parseInt(value%60);// 秒
    let min = parseInt( value/60);// 分
    let result = "";
    if(sec<10){
        sec = '0'+sec;
    }
    if(min == 0){
        min='00';
    }
    else if(min<10){
        min='0'+min;
    }
    result = min+':'+sec;
    return result;
}




