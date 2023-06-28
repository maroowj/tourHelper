jQuery(document).ready(function($) {

    $("input[type=text], textarea").attr("spellcheck", false);

    /*모바일 메뉴*/
    $('.menuBar').click(function(){
        $('.m-nav-wrap').addClass('active');
        $('.bg-cover').addClass('active');
    });

    $('.close-btn, .bg-cover, .close, .modal-bg-cover').click(function(){
        $('.m-nav-wrap').removeClass('active');
        $('.bg-cover').removeClass('active');
        $('.modal').css('display','none');
    });

    /*견학신청 modal*/
    $('.detail-btn .reserve-btn').click(function(){
        if (matchMedia("screen and (max-width: 768px)").matches) {
            $('.m-modal').css('display','block');
        } else {
            $('.pc-modal').css('display','block');
        }
    });

    /*예약가능 modal*/
    //$('.sch-type2').click(function(){
    $("#dates").on("click", ".sch-type2", function(){
        if (matchMedia("screen and (max-width: 768px)").matches) {
            $('.m-reserve-modal').css('display','block');
        } else {
            $('.pc-reserve-modal').css('display','block');
        }
    });

    /*예약확인 modal*/
    //$('.sch-type3').click(function(){
    $("#dates").on("click", ".sch-type3", function(){
        $('.confirm-modal').css('display','block');
        $('#confirm-modal').attr("seq", $(this).attr("reservationseq"));
    });

    /*예약정보 modal*/
    /*$('.confirm-btn').click(function(){
       $('.confirm-modal').css('display','none');
        if (matchMedia("screen and (max-width: 768px)").matches) {
            $('.m-info-modal').css('display','block');
        } else {
            $('.pc-info-modal').css('display','block');
        }
    });*/
    /*예약수정 modal*/
    $('.update-btn').click(function(){
        $('.info-modal').css('display','none');
    });

    /*달력*/
    /*$("#datepicker").datepicker({
        language: 'ko'
    });*/


    /*관리자페이지*/
    /*좌측메뉴 연결*/
    // $('.leftMenu-inner ul li a').click(function(){
    //     $('.leftMenu-inner ul li a').removeClass('on');
    //     $(this).addClass('on');
    // });

    /*모달창*/
    // $('.company-table table tbody tr td.modal-open').click(function(){
    //    $('.course-modal').css('display','block');
    // });
    //
    // $('.modal-open-table table tbody tr').click(function(){
    //     $('.course-modal').css('display','block');
    // });

    $("#reservationList").on("click", ".info", function() {
        // $('.modal-open-table table tbody tr td.modal-open').click(function() {
        $('.admin-info-modal').css('display', 'block');
    });

    $("#universityList").on("click", ".info", function() {
    // $('.modal-open-table table tbody tr td.modal-open').click(function() {
        $('.course-modal').css('display', 'block');
    });


    $('.company-add-btn').click(function(){
       $('.company-add-modal').css('display','block');
    });

    /*$('.company-update-btn').click(function(){
        $('.company-update-modal').css('display','block');
    });*/

    /*링크연결*/
    // $(".view-update").click(function(){
    //     $(location).attr("href", "../admin2/bannerUpdate")
    // });

    //$(".goReservation").click(function(){
    $("#header").on("click", ".goReservation", function(){
        $('.pc-modal').css('display','block');
    });

    $(".footerInfo").click(function(){
        //$('.footerModal').css('display','block');
        $(".footerModal").find(".title").text($(this).text());
    });

    // 전체선택
    $('.all-check').click(function(){
        if($('.all-check').prop('checked')){
            $('.checked').prop('checked', true);
        }else{
            $('.checked').prop('checked', false);
        }
    });

    // 개별선택
    $('.main-content').on("click", ".checked", function() {
        if ($('.checked').length == $('.checked:checked').length) {
            $('.all-check').prop('checked', true);
        } else {
            $('.all-check').prop('checked', false);
        }
    });

    // 상담신청 관리
    $('.consultPage-wrap .content .part.chat .send-area .send-wrap .add-file-area > div:nth-child(1) span').on('click', function(){
        $(this).next('input.photo').click();
    });
    $('.consultPage-wrap .content .part.chat .send-area .send-wrap .add-file-area > div:nth-child(2) span').on('click', function(){
        $(this).next('input.file').click();
    });

    function bottomButtonConsultPC(){
        let scrollTop = document.body.scrollTop;
        let innerHeight = document.body.innerHeight;
        let scrollHeight = document.body.scrollHeight;
        let clientHeight = document.body.clientHeight;
        let pcMsgContent = document.querySelector('.consultPage-wrap .content .part.chat .chat-area .message-area');

        // pc 상담채팅
        $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').on('click', function(){
            pcMsgContent.scrollTop =  pcMsgContent.scrollHeight;
            $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').hide();
        });
    }
    $('.consultPage-wrap .content .part.chat .chat-area .message-area').on('scroll', function(){
        bottomButtonConsultPC();
    });
    // 상담하기
    // 상담 리스트 선택 시 해당 div영역 선택색상 활성화
    $('.consultPage-wrap .content .part.list .message-area .message-wrap').on('click', function(){
        $(this).addClass('active');
    });
    //이미지 미리보기
    $('.consultPage-wrap .content .part.chat .chat-area .message-area .message .img').on('click', function(){
        $('#imgDetailModal').css('display', 'flex');
    });
    // 이미지 상세보기 modal
    $('#imgDetailModal .modal-body').on('click', function (){
        $('#imgDetailModal .modal-header').toggleClass('d-none');
        $('#imgDetailModal .modal-footer').toggleClass('d-none');
    });
    let link = document.location.href;
    if(link.includes('/admin/consult')){
        $('#memberList').on('click', '.list', function (){
            $(this).parent().parent().parent().addClass('d-none');
            memberSeq = $(this).attr('seq');
            getCategoryList(memberSeq);
            $('.consultPage-wrap').removeClass('d-none');
            $('i.prev').removeClass('d-none');
            $("#search-wrap").addClass("d-none");
        });
        $('i.prev').on('click', function (){
            $('.consultPage-wrap').addClass('d-none');
            $('.consultMemberList-wrap').removeClass('d-none');
            $(this).addClass('d-none');
            $("#search-wrap").removeClass("d-none");
            category=null;
        });
    }

    $('.consultPage-wrap .content .part.chat .chat-area .message-area').on('scroll', function(){
        bottomButtonConsultPC();
    });

    function bottomButtonConsultPC(){
        let scrollTop = document.body.scrollTop;
        let innerHeight = document.body.innerHeight;
        let scrollHeight = document.body.scrollHeight;
        let clientHeight = document.body.clientHeight;
        let pcMsgContent = document.querySelector('.consultPage-wrap .content .part.chat .chat-area .message-area');
        // console.log(pcMsgContent.scrollTop);
        // if((pcMsgContent.scrollTop + pcMsgContent.height == pcMsgContent.height) == false){
        //     // console.log(pcMsgContent.height);
        //     $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').show();
        // }else{
        //     $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').hide();
        // }


        if(pcMsgContent.scrollTop <= 10){
            // console.log(pcMsgContent.height);
            // $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').show();
            $('.consultPage-wrap .content .part.chat .view-prev-area').removeClass('d-none');
        }else{
            // $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').hide();
            $('.consultPage-wrap .content .part.chat .view-prev-area').addClass('d-none');
        }

        // pc 상담채팅
        $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').on('click', function(){
            pcMsgContent.scrollTop =  pcMsgContent.scrollHeight;
            $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').hide();
        });
    }
});





























