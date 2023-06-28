function toastModal(txt, reload, sec) {
    let secs = 1000;
    if(sec == undefined || sec == null) secs = 1000;
    else secs = sec;

    const newDiv = document.createElement('div');
    const newText = document.createTextNode(txt);

    newDiv.appendChild(newText);

    // 리사이징
    // $(window).resize(function() {
    //     resize();
    // });

    // 페이지 로드
    // $(document).ready(function(){
    //     resize();
    // });

        if(matchMedia("screen and (max-width: 1199px)").matches){
            $(newDiv).css({
                "position":"fixed",
                "top":"50%",
                "left":"50%",
                "width":"100%",
                "max-width":"calc(100% - 40px)",
                "transform":"translateX(-50%)",
                "backgroundColor":"rgba(43, 53, 53, 0.7)",
                "backdrop-filter":"blur(10px)",
                // "box-shadow":"0px 0px 20px rgb(43 53 53 / 10%)",
                "padding":"1rem 30px",
                "border-radius":"10px",
                "color":"var(--white)",
                "font-size":"1rem",
                "z-index":"99999",
                "text-align":"center",
                "line-height":"1.5rem",
                "font-weight":"500"
            });
        }
        if(matchMedia("screen and (min-width: 1200px)").matches){
            $(newDiv).css({
                "position":"fixed",
                "top":"50%",
                "left":"50%",
                "width":"fit-content",
                "max-width":"calc(100% - 60vw)",
                "transform":"translateX(-50%)",
                "backgroundColor":"rgba(43, 53, 53, 0.7)",
                "backdrop-filter":"blur(10px)",
                // "box-shadow":"0px 0px 20px rgb(43 53 53 / 10%)",
                "padding":"1rem 30px",
                "border-radius":"10px",
                "color":"var(--white)",
                "font-size":"1rem",
                "z-index":"99999",
                "text-align":"center",
                "line-height":"1.5rem",
                "font-weight":"500"
            });
        }

    document.body.appendChild(newDiv);

    setTimeout(function () {
        $(newDiv).animate({
            opacity: "hide"
        });
    }, secs);
}

function fConvertDateFrom(string) {
    let year = string.substr(0, 4);
    let month = string.substr(5, 2);

    let day = string.substr(8, 2);

    let returnDate = new Date(year, parseInt(month) - 1, day);
    returnDate.setHours(returnDate.getHours() + 9);

    let returnStr = returnDate.toISOString().replace("T", " ").replace(/\..*/, '');
    return returnStr;
}

function fConvertDateTo(string) {
    let year = string.substr(0, 4);
    let month = string.substr(5, 2);

    let day = (parseInt(string.substr(8, 2)) + 1) + '';

    let returnDate = new Date(year, parseInt(month) - 1, day);
    returnDate.setHours(returnDate.getHours() + 9);

    let returnStr = returnDate.toISOString().replace("T", " ").replace(/\..*/, '');
    return returnStr;
}

function fPagination(data, page) {
    let num = "";
    let reNum = parseInt(data.number / 5);
    let pre = "";
    let next = "";

    // 이전버튼 (5페이지씩 이동, 처음으로)
    if (reNum >= 1 && page > 5) {
        pre = "<a gopage='" + (reNum * 5) + "'><i id='pre' class='icon-green-arrow-left'></i></a>";
    }

    // 다음버튼 (5페이지씩 이동, 마지막으로)
    if (parseInt((data.totalPages - 1) / 5) != reNum && data.totalPages > 5) {
        next = "<a gopage='" + ( reNum * 5 + 6 ) + "'><i id='next' class='icon-green-arrow-left'></i></a>";
    }

    for ( let idx = 1; idx <= data.totalPages; idx++ ) {
        if ( idx <= reNum * 5 + 5 && idx > reNum * 5 ) {
            if (idx == page) {
                num += "<a class='num active' gopage=" + idx + ">" + idx + "</a>";
            } else {
                num += "<a class='num' gopage=" + idx + ">" + idx + "</a>";
            }
        }
    }
    $(".pagination").html(pre + num + next);
}

// 천단위 콤마
function fcomma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

// 천단위 콤마 제거
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

function fConvertHour(second) {
    let hour = parseInt((second % 3600) / 60) < 10 ? '0' + parseInt((second % 3600) / 60) : parseInt((second % 3600) / 60);

    return hour;
}
function fConvertMin(second) {
    let min = parseInt(second % 60) < 10 ? '0' + parseInt(second % 60) : parseInt(second % 60);

    return min;
}

