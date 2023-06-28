jQuery(document).ready(function($) {
    // 헤더(header)
    $('.header-wrap i.prev').on('click', function (){
        window.history.back();
    });
    $('.header-wrap i.consult').on('click', function (){
        //상담채팅 열기
    });
    $('.header-wrap i:nth-child(1)').on('click', function(){
        if($(this).hasClass('prev')){
            window.history.back();
        }
        if($(this).hasClass('consult')){
            location.href='/consult'
        }
    });
    $('.header-wrap i:nth-child(2)').on('click', function(){
        location.href='/' //메인페이지로 이동
    });
    $('.header-wrap i:nth-child(3)').on('click', function(){
        $('.hamburger-menu').css('display', 'flex');
    });

    $('.header-wrap-pc > div > div > div').on('click', function (){
        if($(this).hasClass('menu1')){location.href='/abroad/info'}
        if($(this).hasClass('menu2')){location.href='/E74/apply'}
        if($(this).hasClass('menu3')){location.href='/calculator'}
        if($(this).hasClass('menu4')){location.href='/business/list'}
        if($(this).hasClass('menu5')){location.href='/myanmar/tour/list'}
        if($(this).hasClass('menu6')){location.href='/domestic/tour/list'}
        if($(this).hasClass('mypage')){location.href='/mypage'}
        if($(this).hasClass('login')){location.href='/login'}
        if($(this).hasClass('join')){location.href='/join'}
    });

    // 상단 유학진행알림 헤더팝업 바로가기 선택 시
    $('.pop-up-wrap > div > .go-direct').on('click', function(){
        // location.href='/mypage'
        setTimeout(function (){
            $('#progressModal').css('display', 'flex');
        }, 0);
    });

    // 하단 네비게이션 바
    $('.navi-bar > div > div > div').on('click', function (){
        if($(this).hasClass('menu1')){location.href='/abroad/info'}
        if($(this).hasClass('menu2')){location.href='/E74/apply'}
        if($(this).hasClass('menu3')){location.href='/consult'}
        if($(this).hasClass('menu4')){location.href='/calculator'}
        if($(this).hasClass('menu5')){location.href='/mypage'}
    });


    // url
    let link = document.location.href;
    if(link.includes('/tourhelper.co.kr')){
        $('.header-wrap i:nth-child(1)').addClass('consult');
        $('.header-wrap i:nth-child(1)').removeClass('prev');
        $('.page-title').hide();
        $('.btn-consult').show();
    }else{
        $('.btn-consult').hide();
    }
    if(link.includes('/login')){
        $('.pop-up-wrap').hide();
    }
    if(link.includes('/join')){
        $('.pop-up-wrap').hide();
    }
    if(link.includes('/calculator')){
        $('.pop-up-wrap').hide();
    }
    if(link.includes('/abroad/apply')){
        // 학교선택
        $('.abroad-apply-wrap .content-wrap .input-wrap.select.university input').on('click', function(){
            $('#selUniversityModal').css('display', 'flex');
        });
        // 기간선택
        $('.abroad-apply-wrap .content-wrap .input-wrap.select.period input').on('click', function(){
            $('#selPeriodModal').css('display', 'flex');
        });
        // 한국어 능력시험 선택
        $('.abroad-apply-wrap .content-wrap .input-wrap.select.test input').on('click', function(){
            $('#selTestModal').css('display', 'flex');
        });
        // 국가 선택
        $('.abroad-apply-wrap .content-wrap .input-wrap.select.country input').on('click', function(){
            $('#selCountryModal').css('display', 'flex');
        });
    }
    if(link.includes('/mypage')){
        $('.btn-consult').show();

        // 프로필 사진 등록/변경
        $('.myPage-wrap .part.profile .profile-wrap .btn-addPhoto').on('click', function (){
            $(this).next('input.photo').click();
        });

        // 상담채팅 modal
        // 상담 내역 리스트 선택 시 상담 채팅modal 나타나기
        $('.myPage-wrap .content-wrap > div > .flex-wrap .part.consult .message-wrap').on('click', function(){
            $('#messageModal').css('display', 'flex');
        });
        // 이미지 선택 시 이미지 미리보기modal 나타나기
        // $('#messageModal .modal-content .chat-area .message-area .message .img').on('click', function(){
        //     $('#imgDetailModal').css('display', 'flex');
        // });
        $('.myPage-wrap .part.profile .info-wrap > div > span:nth-child(3)').on('click', function(){
            location.href='/profile'
        });
        // 재직여부 선택
        // 일자리 추가
        $('#addEmploymentModal .input-wrap.select.status input').on('click', function(){
            $('#addSelEmploymentModal').css('display', 'flex');

            $("#selSaveWorking").off("click").on("click", function () {
                saveWorking = $('input[name="selEmp1"]:checked').val();
                let strWorking = '';
                if (saveWorking == 0) {
                    strWorking = '퇴사';
                } else if (saveWorking == 1) {
                    strWorking = '휴직';
                    $("#saveOutYear").val("");
                    $("#saveOutMonth").val("");
                    $("#saveOutDate").val("")
                } else if (saveWorking == 2) {
                    strWorking = '재직중';
                    $("#saveOutYear").val("");
                    $("#saveOutMonth").val("");
                    $("#saveOutDate").val("")
                }
                $("#saveWorking").val(strWorking);
                $(this).parent().parent().parent().fadeOut(150);
            });
        });
        // 일자리 편집
        $('#editEmploymentModal .input-wrap.select.status input').on('click', function(){
            $('#editSelEmploymentModal').css('display', 'flex');

            $("#selEditWorking").off("click").on("click", function () {
                editWorking = $('input[name="selEmp2"]:checked').val();
                let strWorking = '';
                if (editWorking == 0) {
                    strWorking = '퇴사';
                } else if (editWorking == 1) {
                    strWorking = '휴직';
                    $("#editOutYear").val("");
                    $("#editOutMonth").val("");
                    $("#editOutDate").val("")
                } else if (editWorking == 2) {
                    strWorking = '재직중';
                    $("#editOutYear").val("");
                    $("#editOutMonth").val("");
                    $("#editOutDate").val("")
                }
                $("#editWorking").val(strWorking);
                $(this).parent().parent().parent().fadeOut(150);
            });

        });
    }
    if(link.includes('/profile')){
        $('.inputPrivacy-wrap .content-wrap .input-wrap.interest1 input').on('click', function(){
            $('#selInterestsModal1').css('display', 'flex');
        });
        $('.inputPrivacy-wrap .content-wrap .input-wrap.interest2 input').on('click', function(){
            $('#selInterestsModal2').css('display', 'flex');
        });
        $('.inputPrivacy-wrap .content-wrap .input-wrap.country input').on('click', function(){
            $('#selCountryModal').css('display', 'flex');
        });
    }
    if(link.includes('/abroad/info')){
        //상단 스크롤 메뉴의 sticky속성 먹게 하기 위한 overflow 속성 제거
        $('html, body').css({
            'overflow':'unset'
        });

        let abroadInfoWrapHeight = document.querySelector('.abroad-info-wrap').offsetHeight;
        // let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
        $('.abroad-info-wrap').css('height', abroadInfoWrapHeight);
    }
    if(link.includes('/E74/apply')){
        $('.E74-apply-wrap .content-wrap .input-wrap.country input').on('click', function(){
            $('#selCountryModal').css('display', 'flex');
        });
    }
    if(link.includes('/E74/apply/form')){
        //상단 스크롤 메뉴의 sticky속성 먹게 하기 위한 overflow 속성 제거
        $('html, body').css({
            'overflow':'unset'
        });
        $('.pop-up-wrap').hide();
    }
    if(link.includes('/business/detail')){
        $('#btnJoin').on('click', function (){
            if(!chkLogin) {
                alert("로그인이 필요합니다.");
                location.href="/login";
                return;
            }
            getMemberDetails();
            $('#reserveBusinessModal').css('display', 'flex');
        });
    }
    if(link.includes('/domestic/tour/detail')){
        $('#btnJoin').on('click', function (){
            if(!chkLogin) {
                alert("로그인이 필요합니다.");
                location.href="/login";
                return;
            }
            getMemberDetails();
            $('#domesticTravelModal').css('display', 'flex');
        });
    }
    if(link.includes('/myanmar/tour/detail')){
        $('#btnJoin').on('click', function (){
            if(!chkLogin) {
                alert("로그인이 필요합니다.");
                location.href="/login";
                return;
            }
            getMemberDetails();
            $('#myanmarTravelModal').css('display', 'flex');
        });
    }

    /** 221201 우람 수정 **/
    // 이전 상담이력 보기/닫기
    $('.cancel-view-prev').on('click', function(){
        $(this).addClass('d-none');
        $('.view-prev').parent().removeClass('d-none');
    });
    $('.view-prev').on('click', function(){
        $(this).parent().addClass('d-none');
        $('.cancel-view-prev').removeClass('d-none');
    });


    /** 221117 우람 수정 **/
    // 햄버거 메뉴
    // 미로그인 시 '로그인 하세요' 영역 선택 시 로그인으로 페이지 이동
    $('.hamburger-menu > div .profile-wrap > div:nth-child(3) > span').on('click', function(){
        if($(this).hasClass("noLogin")) {
            location.href='/login'
        }else if($(this).hasClass("yesLogin")) {
            location.href='/profile'
        }
    });

    //수정 아이콘 선택 시 개인정보 수정페이지로 이동
    $('.hamburger-menu > div .profile-wrap > div:nth-child(3) i.edit').on('click', function(){
        location.href='/profile'
    });

    $('.hamburger-menu .hamburger-menu-list div').on('click', function (){
        if($(this).hasClass('menu1')){location.href='/mypage'}
        if($(this).hasClass('menu2')){location.href='/abroad/info'}
        if($(this).hasClass('menu3')){location.href='/consult'}
        if($(this).hasClass('menu4')){location.href='/E74/apply'}
        if($(this).hasClass('menu5')){location.href='/calculator'}
        if($(this).hasClass('menu6')){location.href='/business/list'}
        if($(this).hasClass('menu7')){location.href='/myanmar/tour/list'}
        if($(this).hasClass('menu8')){location.href='/domestic/tour/list'}
        if($(this).hasClass('logout')){location.href='/user/logout'}
    });


    //모달(modal)
    // $('.modal').css('display', 'none');
    $('.modal-back, .modal-back2').on('click', function(){
        $(this).parent().fadeOut(150);
        // $('.modal input:text').val('');
        // $('.modal input:file').val('');
        // $('.modal input:password').val('');
        // $('.modal input:checkbox').prop('checked', false);
        // $('.modal input:radio').prop('checked', false);
        // $('.modal textarea').val('');
        // $('.modal select option').prop('selected', false);
        // $('.modal input').removeClass('complete');
    });
/*    $('.modal-footer button').on('click', function (){
        if($(this).hasClass('btn-download') == false)
        $(this).parent().parent().parent().fadeOut(150);
        $('.modal input:text').val('');
        $('.modal input:file').val('');
        $('.modal input:password').val('');
        $('.modal input:checkbox').prop('checked', false);
        // $('.modal input:radio').prop('checked', false);
        $('.modal textarea').val('');
        $('.modal select option').prop('selected', false);
        $('.modal input').removeClass('complete');
    });*/
    $('.modal i.close').on('click', function(){
        $(this).parent().parent().parent().fadeOut(150);
        // $('.modal input:text').val('');
        // $('.modal input:file').val('');
        // $('.modal input:password').val('');
        // $('.modal input:checkbox').prop('checked', false);
        // $('.modal input:radio').prop('checked', false);
        $('.modal textarea').val('');
        // $('.modal select option').prop('selected', false);
        // $('.modal input').removeClass('complete');
    });
    $('.hamburger-menu .close').on('click', function(){
        $(this).parent().fadeOut(150);
    });
    // 이미지 상세보기 modal
    $('#imgDetailModal .modal-body').on('click', function (){
        $('#imgDetailModal .modal-header').toggleClass('d-none');
        $('#imgDetailModal .modal-footer').toggleClass('d-none');
    });
    // 상담 채팅 modal(자동으로 최하단 스크롤)
    $(function (){
        messagemodalScrollBottom();
    });
    function messagemodalScrollBottom(){
        let messagemodal = document.querySelector('#messageModal .modal-content');
        messagemodal.scrollTop = messagemodal.scrollHeight;
        setInterval(updateScroll, 0);

        var scrolled = false;
        function updateScroll(){
            if(!scrolled){
                messagemodal.scrollTop = messagemodal.scrollHeight;
            }
        }
        $(messagemodal).on('scroll', function(){
            scrolled=true;
        });
    }
    $('#messageModal .modal-footer .send-area > div:nth-child(1)').on('click', function(){
        messagemodalScrollBottom();
        if($(this).hasClass('add-file')){
            if(matchMedia("screen and (max-width: 1199px)").matches){
                $(this).removeClass('add-file');
                $(this).addClass('close');
                $('#messageModal .modal-footer .add-file-area').removeClass('d-none');
                if($('#messageModal .modal-footer .add-file-area').hasClass('d-none') == false){
                    $('#messageModal .modal-content').css({
                        'height':'calc(100vh - 205px)'
                    });
                    $('#messageModal .modal-footer').css({
                        'padding-top':'unset'
                    });
                    $('#messageModal .modal-footer .send-area > div.add-file').addClass('close');
                }else{
                    $('#messageModal .modal-content').css({
                        'height':'calc(100vh - 155px)'
                    });
                }
            }
            if(matchMedia("screen and (min-width: 1200px)").matches){
                $(this).removeClass('add-file');
                $(this).addClass('close');
                $('#messageModal .modal-footer .add-file-area').removeClass('d-none');
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 335px)'
                });
                $('#messageModal .modal-footer').css({
                    'padding-top':'unset'
                });
                $('#messageModal .modal-footer .send-area > div.add-file').addClass('close');
            }
        }else if($(this).hasClass('close')){
            if(matchMedia("screen and (max-width: 1199px)").matches){
                $(this).addClass('add-file');
                $(this).removeClass('close');
                $('#messageModal .modal-footer .add-file-area').addClass('d-none');
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 155px)'
                });
                $('#messageModal .modal-footer').css({
                    'padding-top':'10px'
                });
            }
            if(matchMedia("screen and (min-width: 1200px)").matches){
                $(this).addClass('add-file');
                $(this).removeClass('close');
                $('#messageModal .modal-footer .add-file-area').addClass('d-none');
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 285px)'
                });
                $('#messageModal .modal-footer').css({
                    'padding-top':'10px'
                });
            }
        }
    });

    /** 221202 우람 추가 mobile 상담 메시지 및 파일 전송 **/
    $('#messageModal .modal-footer .send-area > .btn-send').on('click', function(){
        let content = $("#msg-content-mobile").val();
        if(content==null || content=="") {
            toastModal("메시지를 입력하세요");
            return;
        }
        mobileSendMsg(content, null, null);
        $("#msg-content-mobile").val("");
        $("#msg-content-mobile").focus();

    });

    $("#messageModal").on("change", ".photo", function() {
        let image = $("#mobile-image")[0].files[0];
        mobileSendMsg(null, image, null);
        $("#msg-content-mobile").focus();
    });

    $("#messageModal").on("change", ".file", function() {
        let file = $("#mobile-file")[0].files[0];
        mobileSendMsg(null, null, file);
        $("#msg-content-mobile").focus();
    });
    /** mobile **/

    // 이미지/파일 첨부
    $('#messageModal .modal-footer .add-file-area .add-album span').on('click', function(){
        $(this).next('input.photo').click();
    });
    $('#messageModal .modal-footer .add-file-area .add-file span').on('click', function(){
        $(this).next('input.file').click();
    });
    $('.consultPage-wrap .content .part.chat .send-area .send-wrap .add-file-area > div:nth-child(1) span').on('click', function(){
        $(this).next('input.photo').click();
    });
    $('.consultPage-wrap .content .part.chat .send-area .send-wrap .add-file-area > div:nth-child(2) span').on('click', function(){
        $(this).next('input.file').click();
    });


    //탑버튼
    // $('.btn-top').hide();
    topButton();
    function topButton(){
        if($(this).scrollTop() > 10){
            $('.btn-top').show();
        }else{
            $('.btn-top').hide();
        }

        $('.btn-top').click(function(){
            //해당 컨텐츠로 스크롤 이동
            window.scrollTo(0, 0);
            // window.scrollTo({top: 0, behavior: "smooth"});
        });
    }

    /** 221202 우람 수정 **/
    //맨 아래로 버튼
    function bottomButton(){
        let scrollTop = document.body.scrollTop;
        let innerHeight = document.body.innerHeight;
        let scrollHeight = document.body.scrollHeight;
        let clientHeight = document.body.clientHeight;
        let msgContent = document.querySelector('#messageModal .modal-content');

        if(msgContent.scrollTop <= 10){
            // console.log(pcMsgContent.height);
            // $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').show();
            $('.modal-wrap .modal-body .view-prev-area').removeClass('d-none');
        }else{
            // $('.consultPage-wrap .content .part.chat .send-area .btn-bottom').hide();
            $('.modal-wrap .modal-body .view-prev-area').addClass('d-none');
        }

        // if((msgContent.scrollTop + msgContent.height == msgContent.height) == false){
        //     $('#messageModal .btn-bottom').show();
        // }else{
        //     $('#messageModal .btn-bottom').hide();
        // }

        // $('.btn-bottom').click(function(){
            //맨 아래로 스크롤 이동
        //     window.scrollTo(0,document.body.scrollHeight);
        //     $('.btn-bottom').hide();
        // });

        // if($(window).scrollTop() + $(window).height() == $(document).height()){
        //     $('.btn-button').hide();
        // }else{
        //     $('.btn-button').show();
        // }

        // 상담채팅 modal
        $('#messageModal .btn-bottom').on('click', function(){
            msgContent.scrollTop =  msgContent.scrollHeight;
            $('#messageModal .btn-bottom').hide();
        });
    }

    /** 221201 우람 수정 **/
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
    // 상담채팅
    $('#messageModal .modal-content').on('scroll', function(){
        bottomButton();
    });
    $('.consultPage-wrap .content .part.chat .chat-area .message-area').on('scroll', function(){
        bottomButtonConsultPC();
    });


    // 상단 헤더 팝업(진행단계 알림)
    function popUp(){
        let scroll = $(window).scrollTop();

        if(scroll > 1){
        }else{
        }
    }


    //메인페이지 내 상담신청 section 내용 롤링
    function rollingConsult(){
        const swiperConsult = new Swiper('.part.consult .swiper', {
            //기본 셋팅
            //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
            direction: 'vertical',

            //반복 기능 true 사용가능 false 사용불가
            loop: true,

            speed: 2000,

            // parallax: true,

            autoplay: {
                delay: 0,
                disableOnInteraction: true,
            },
        });
    }


    function responsiveBanner(){
        if(matchMedia("screen and (max-width: 650px)").matches){
            $('.banner-wrap .banner-area .img-area .mainbanner1').attr('src', '/tourhelperUser/images/banner/mobile/mainbanner1.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner2').attr('src', '/tourhelperUser/images/banner/mobile/mainbanner2.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner3').attr('src', '/tourhelperUser/images/banner/mobile/mainbanner3.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner4').attr('src', '/tourhelperUser/images/banner/mobile/mainbanner4.jpg');
        }
        if(matchMedia("screen and (min-width: 651px)").matches){
            $('.banner-wrap .banner-area .img-area .mainbanner1').attr('src', '/tourhelperUser/images/banner/pc/mainbanner1.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner2').attr('src', '/tourhelperUser/images/banner/pc/mainbanner2.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner3').attr('src', '/tourhelperUser/images/banner/pc/mainbanner3.jpg');
            $('.banner-wrap .banner-area .img-area .mainbanner4').attr('src', '/tourhelperUser/images/banner/pc/mainbanner4.jpg');
        }
    }


    // 리사이징
    $(window).resize(function() {
        responsive();
        // toastModal("화면 리사이즈 화면 리사이즈 화면 리사이즈 화면 리사이즈", 1000);
        responsiveBanner();
    });


    // 페이지 로드
    $(document).ready(function(){
        // 반응형 작업
        mainBannerSlideEffect();
        responsiveBanner();
        responsive();
        topButton();
        rollingConsult();
    });


    // 페이지 스크롤
    $(window).scroll(function (){
        // 탑버튼
        topButton();
    });


    function responsive(){
        // mobile & tablet
         if(matchMedia("screen and (max-width: 1199px)").matches){
            $('.header-wrap-pc').hide();
            $('.content-wrap .part .list-wrap').removeClass('swiper');
            $('.content-wrap .part .list-wrap > div').removeClass('swiper-wrapper');
            $('.content-wrap .part .list-wrap > div').removeClass('swiper-slide-duplicate');
            $('.content-wrap .part .list-wrap > div').removeClass('swiper-slide-duplicate-next');
            $('.content-wrap .part .list-wrap > div > div').removeClass('swiper-slide');
            $('.content-wrap .part .title > div span').hide();

            if(link.includes('/tourhelper.co.kr')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'110px'
                });
                $('.btn-consult').hide();
                $('.navi-bar').show();
            }else{
                $('.header-wrap i:nth-child(1)').addClass('consult');
                $('.header-wrap i:nth-child(1)').removeClass('prev');
                $('.navi-bar').hide();
            }
            if(link.includes('/login')){
                $('.btn-consult').hide();
            }
            if(link.includes('/join')){
                $('.btn-consult').hide();
            }
            if(link.includes('/abroad/info')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'120px'
                });

                // 상단 스크롤 탭 선택 시 포커스효과
                // 처음 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(1)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '0',
                        width: '32.53px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(1)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    // window.scrollTo({top: 0, behavior: "smooth"});
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + 20, behavior: 'smooth'});
                });
                // 과정소개 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(2)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '52.53px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(2)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + 60, behavior: 'smooth'});
                });
                // 추천대학 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(3)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '142.31px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(3)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    let partTwoHeight = document.querySelector('.abroad-info-wrap .part.two').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + partTwoHeight + 100, behavior: 'smooth'});
                });
                // 진학사례 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(4)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '233.09px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(4)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    let partTwoHeight = document.querySelector('.abroad-info-wrap .part.two').offsetHeight;
                    let partThreeHeight = document.querySelector('.abroad-info-wrap .part.three').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + partTwoHeight + partThreeHeight + 140, behavior: 'smooth'});
                });
            }
            if(link.includes('/abroad/detail')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/consult')){
                $('body').css({
                    'background-color':'#F9FBFB'
                });
                $('.header-wrap').css({
                    'background-color':'#F9FBFB'
                });
                $('.footer-wrap').hide();


                // 상담채팅 열기
                $('.category-list-mobile input + label').on('click', function(){
                    $('#messageModal').css('display', 'flex');
                });
                // 닫기 버튼 선택 시 선택된 상담 카테고리 check속성 해제
                $('#messageModal .modal-header i.close').on('click', function(){
                    $('input:radio[name="selCategory"]').prop('checked', false);
                    chatOn=false;
                });

                //이미지 미리보기
                $('#messageModal .message-area .message .img').on('click', function(){
                    $('#imgDetailModal').css('display', 'flex');
                });
            }
            if(link.includes('/mypage')){
                $('.btn-top').css({
                    'bottom':'95px'
                });
                $('.btn-consult').show();
            }
            if(link.includes('/business/detail')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/myanmar/tour/detail')){
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/domestic/tour/detail')){
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }

            if($('#messageModal .modal-footer .send-area > div:nth-child(1)').hasClass('close')){
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 205px)'
                });
                $('#messageModal .modal-footer').css({
                    'padding-top':'unset'
                });
            }else{
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 155px)'
                });
            }

            //textarea placeholder
            $('.send-area .input-area textarea').attr('placeholder', '메세지 입력');

        }
    

        // pc
        if(matchMedia("screen and (min-width: 1200px)").matches){
            $('.header-wrap-pc').show();
            $('.content-wrap .part .list-wrap').addClass('swiper');
            $('.content-wrap .part .list-wrap > div').addClass('swiper-wrapper');
            $('.content-wrap .part .list-wrap > div > div').addClass('swiper-slide');
            $('.content-wrap .part.helper .list-wrap').removeClass('swiper');
            $('.content-wrap .part.helper .list-wrap > div').removeClass('swiper-wrapper');
            $('.content-wrap .part.helper .list-wrap > div').removeClass('swiper-slide-duplicate');
            $('.content-wrap .part.helper .list-wrap > div').removeClass('swiper-slide-duplicate-next');
            $('.content-wrap .part.helper .list-wrap > div .box').removeClass('swiper-slide');
            $('.content-wrap .part .title > div span').show();
            if(link.includes('/tourhelper.co.kr')){
                $('.footer-wrap').css({
                    'padding-bottom':'60px'
                });
                $('.btn-top').css({
                    'bottom':'30px'
                });
            }else{
                $('.header-wrap i:nth-child(1)').addClass('consult');
                $('.header-wrap i:nth-child(1)').removeClass('prev');
            }
            if(link.includes('/abroad/info')){
                $('.btn-top').css({
                    'bottom':'120px'
                });

                // 이미지 슬라이드
                // 추천대학
                const swiperAbroad = new Swiper('.abroad-info-wrap .part.three .swiper', {
                    //기본 셋팅
                    //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
                    direction: 'horizontal',

                    //한번에 보여지는 페이지 숫자
                    slidesPerView: 3,

                    //한 그룹 당 묶여지는 슬라이드 수
                    slidesPerGroup: 1,

                    //페이지와 페이지 사이의 간격
                    spaceBetween: 16,

                    //드래그 기능 true 사용가능 false 사용불가
                    debugger: true,

                    //마우스 휠기능 true 사용가능 false 사용불가
                    mousewheel: false,

                    //반복 기능 true 사용가능 false 사용불가
                    loop: true,

                    speed: 500,
                
                    parallax: true,

                    //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
                    centeredSlides: false,

                    // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
                    // effect: 'fade',
                    
                    loopFillGroupWithBlank: false,

                    autoplay: {
                        delay: 2000,
                        disableOnInteraction: false,
                    },
        
                    scrollbar: { draggable: true, el: null },
                        
                    navigation: {   // 버튼
                        prevEl: ".abroad-info-wrap .part.three .prev",
                        nextEl: ".abroad-info-wrap .part.three .next",
                    }
                });

                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });

                // 상단 스크롤 탭 선택 시 포커스효과
                // 처음 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(1)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '0',
                        width: '32.53px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(1)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    // window.scrollTo({top: 0, behavior: "smooth"});
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + 60, behavior: 'smooth'});
                });
                // 과정소개 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(2)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '52.53px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(2)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + 100, behavior: 'smooth'});
                });
                // 추천대학 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(3)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '142.31px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(3)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    let partTwoHeight = document.querySelector('.abroad-info-wrap .part.two').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + partTwoHeight + 180, behavior: 'smooth'});
                });
                // 진학사례 선택
                $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(4)').on('click', function (event){
                    event.preventDefault();
                    $('.abroad-info-wrap .scroll-menu > ul i.underline').animate({
                        left: '233.09px',
                        width: '69.78px',
                    }, 1, 'easeInOutQuart');
                    $('.abroad-info-wrap .scroll-menu > ul li').removeClass('focus');
                    $('.abroad-info-wrap .scroll-menu > ul li:nth-of-type(4)').addClass('focus');

                    //해당 컨텐츠로 스크롤 이동
                    let offset = document.querySelector('.abroad-info-wrap .content-wrap').offsetTop;
                    let topMenuHeight = document.querySelector('.header-wrap').offsetHeight;
                    let tabBarHeight = document.querySelector('.abroad-info-wrap .scroll-menu').offsetHeight;
                    let partOneHeight = document.querySelector('.abroad-info-wrap .part.one').offsetHeight;
                    let partTwoHeight = document.querySelector('.abroad-info-wrap .part.two').offsetHeight;
                    let partThreeHeight = document.querySelector('.abroad-info-wrap .part.three').offsetHeight;
                    window.scrollTo({top: offset - (topMenuHeight + tabBarHeight) + partOneHeight + partTwoHeight + partThreeHeight + 260, behavior: 'smooth'});
                });
            }
            if(link.includes('/abroad/detail')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/consult')){
                $('body').css({
                    'background-color':'unset'
                });
                $('.header-wrap').css({
                    'background-color':'var(--white)'
                });
                $('.footer-wrap').show();
                $('#messageModal').hide();

                //이미지 미리보기
                $('.consultPage-wrap .content .part.chat .chat-area .message-area .message .img').on('click', function(){
                    $('#imgDetailModal').css('display', 'flex');
                });

                // 상담채팅 열기
                //mobile용 modal 보이지 않기
                $('.consultPage-wrap .content .part.list .message-area .message-wrap').on('click', function(){
                    $('#messageModal').css('display', 'none');
                });
                // pc용 상담채팅 열기
                $('.consultPage-wrap .content .part.chat .sel-category-area .category-list input + label, .consultPage-wrap .content .part.list .message-area .message-wrap, .consultPage-wrap .content .part.chat .category-list-mobile input + label').on('click', function(){
                    // 상담 채팅영역
                    $('.consultPage-wrap .content .part.chat .no-list').addClass('d-none');
                    // $('.consultPage-wrap .content .part.chat .view-prev-area').removeClass('d-none'); /** 221201 우람 수정 **/
                    $('.consultPage-wrap .content .part.chat .message-area').removeClass('d-none');
                    $('.consultPage-wrap .content .part.chat .send-area').removeClass('d-none');
                    
                    // 상담리스트
                    $('.consultPage-wrap .content .part.list .no-list').addClass('d-none');
                    $('.consultPage-wrap .content .part.list .message-area').removeClass('d-none');

                    let message = document.querySelector('.consultPage-wrap .content .part.chat .chat-area .message-area');
                    message.scrollTop = message.scrollHeight;
                    setInterval(updateScroll, 0);

                    var scrolled = false;
                    function updateScroll(){
                        if(!scrolled){
                            message.scrollTop = message.scrollHeight;
                        }
                    }
                    $(message).on('scroll', function(){
                        scrolled=true;
                    });
                });
            }
            
            if(link.includes('/mypage')){
                $('.btn-top').css({
                    'bottom':'30px'
                });
                $('.btn-consult').hide();
            }
            if(link.includes('/business/detail')){
                $('.footer-wrap').css({
                    'padding-bottom':'200px'
                });
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/myanmar/tour/detail')){
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }
            if(link.includes('/domestic/tour/detail')){
                $('.btn-top').css({
                    'bottom':'120px'
                });
            }

            if($('#messageModal .modal-footer .send-area > div:nth-child(1)').hasClass('close')){
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 335px)'
                });
                $('#messageModal .modal-footer').css({
                    'padding-top':'unset'
                });
            }else{
                $('#messageModal .modal-content').css({
                    'height':'calc(100vh - 285px)'
                });
            }

            //textarea placeholder
            $('.send-area .input-area textarea').attr('placeholder', '메세지 입력');


            //다른 영역 선택 시 사라지기
            $(document).on('click', function(e){ //문서 body를 클릭했을때
                if(e.target.className == "hamburger-menu" || e.target.className == "hamburger"){
                    return false;
                } //내가 클릭한 요소(target)를 기준으로 상위요소에 .hamburger-menu, .hamburger이(가) 없으면 (갯수가 0이라면)
                $(".hamburger-menu").stop().hide(); //fadeOut 막기
            });

    
            // 이미지 슬라이드
            // 메인-유학
            const swiper1 = new Swiper('.main-wrap .content-wrap .part.abroad .swiper', {
                //기본 셋팅
                //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
                direction: 'horizontal',

                //한번에 보여지는 페이지 숫자
                slidesPerView: 3,

                //한 그룹 당 묶여지는 슬라이드 수
                slidesPerGroup: 1,

                //페이지와 페이지 사이의 간격
                spaceBetween: 16,

                //드래그 기능 true 사용가능 false 사용불가
                debugger: true,

                //마우스 휠기능 true 사용가능 false 사용불가
                mousewheel: false,

                //반복 기능 true 사용가능 false 사용불가
                loop: true,

                speed: 500,
            
                parallax: true,

                //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
                centeredSlides: false,

                // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
                // effect: 'fade',
                
                loopFillGroupWithBlank: false,

                autoplay: {
                    delay: 2000,
                    disableOnInteraction: false,
                },
    
                scrollbar: { draggable: true, el: null },
                    
                navigation: {   // 버튼
                    prevEl: ".main-wrap .content-wrap .part.abroad .prev",
                    nextEl: ".main-wrap .content-wrap .part.abroad .next",
                }
            });


            // 이미지 슬라이드
            // 메인-국내 여행정보
            const swiper2 = new Swiper('.main-wrap .content-wrap .part.tour .swiper', {
                //기본 셋팅
                //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
                direction: 'horizontal',

                //한번에 보여지는 페이지 숫자
                slidesPerView: 3,

                //한 그룹 당 묶여지는 슬라이드 수
                slidesPerGroup: 1,

                //페이지와 페이지 사이의 간격
                spaceBetween: 16,

                //드래그 기능 true 사용가능 false 사용불가
                debugger: true,

                //마우스 휠기능 true 사용가능 false 사용불가
                mousewheel: false,

                //반복 기능 true 사용가능 false 사용불가
                loop: true,

                speed: 500,
            
                parallax: true,

                //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
                centeredSlides: false,

                // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
                // effect: 'fade',
                
                loopFillGroupWithBlank: false,

                autoplay: {
                    delay: 2000,
                    disableOnInteraction: false,
                },
    
                scrollbar: { draggable: true, el: null },
                    
                navigation: {   // 버튼
                    prevEl: ".main-wrap .content-wrap .part.tour .prev",
                    nextEl: ".main-wrap .content-wrap .part.tour .next",
                }
            });


            // 투어 도우미 TV Youtube API
            // 2. This code loads the IFrame Player API code asynchronously.
            var tag = document.createElement('script');

            tag.src = "https://www.youtube.com/iframe_api";
            var firstScriptTag = document.getElementsByTagName('script')[0];
            firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

            // 3. This function creates an <iframe> (and YouTube player)
            //    after the API code downloads.
            var player = document.getElementById('player');
            function onYouTubeIframeAPIReady() {
                player = new YT.Player('player', {
                height: '330',
                width: '600',
                videoId: 'M7lc1UVf-VE',
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
                });
            }

            // 4. The API will call this function when the video player is ready.
            function onPlayerReady(event) {
                event.target.playVideo();
            }

            // 5. The API calls this function when the player's state changes.
            //    The function indicates that when playing a video (state=1),
            //    the player should play for six seconds and then stop.
            var done = false;
            function onPlayerStateChange(event) {
                if (event.data == YT.PlayerState.PLAYING && !done) {
                    setTimeout(stopVideo, 6000);
                    done = true;
                }
                if(player.getPlayerState() == 1) console.log("재생중");
                  else if(player.getPlayerState() == 2) console.log("일시중지");
            }
            function stopVideo() {
                player.stopVideo();
            }


            // 상담하기
            // 상담 리스트 선택 시 해당 div영역 선택색상 활성화
            $('.consultPage-wrap .content .part.list .message-area .message-wrap').on('click', function(){
                $(this).addClass('active');
            });
    
        }
    }


    // 메인베너 슬라이딩 효과
    function mainBannerSlideEffect(){
        // 메인페이지 내 메인배너
        const mainBannerswiper = new Swiper('.main-wrap .banner-wrap .swiper', {
            //기본 셋팅
            //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
            direction: 'horizontal',

            //한번에 보여지는 페이지 숫자
            slidesPerView: 1,

            //한 그룹 당 묶여지는 슬라이드 수
            slidesPerGroup: 1,

            //페이지와 페이지 사이의 간격
            spaceBetween: 0,

            //드래그 기능 true 사용가능 false 사용불가
            debugger: true,

            //마우스 휠기능 true 사용가능 false 사용불가
            mousewheel: false,

            //반복 기능 true 사용가능 false 사용불가
            loop: true,

            speed: 1000,

            parallax: true,

            //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 false
            centeredSlides: false,

            // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
            // effect: 'fade',

            loopFillGroupWithBlank: false,

            pagination: {
                el: '.main-wrap .banner-wrap .main-banner-pagination',
                type: 'fraction',
                // clickable: true,
            },

            autoplay: {
                delay: 4000,
                // speed: 5000,
                disableOnInteraction: false,
            },

            scrollbar: { draggable: true, el: null },

            navigation: { // 버튼
                prevEl: ".main-wrap .banner-wrap .pagination-area .prev",
                nextEl: ".main-wrap .banner-wrap .pagination-area .next",
            }
        });
    }


    // 메인페이지 내 국내 여행정보 항목 숨기기
    $('.main-wrap .content-wrap .part.tour').hide();


    //상단 헤더팝업 내 X버튼 클릭 시 사라지기
    $('.pop-up-wrap i.close').on('click', function(){
        $(this).parent().parent().parent().addClass('d-none');
    });



    //퇴직금 계산기
    // 초기화 버튼 선택 시 input 내용 초기화
    $('.calculator-wrap .content-wrap .part .btn-area button.initial').on('click', function(){
        $('.calculator-wrap .content-wrap .part .input-wrap input:text').val('');
        $('.input-wrap input:text').removeClass('complete');
    });
    // 금액 입력 시 콤마 표시
    $('.calculator-wrap .content-wrap .part .input-wrap input.comma').on('input', function(){
        $(this).val(fcomma(parseInt($(this).val())));
        if($(this).val() == '' || $(this).val() == 'NaN'){
            $(this).val('');
        }
    });
    //계산결과 금액에 콤마 표시
    // $('.calculator-wrap .content-wrap .part:nth-child(2) span.comma').text().fcomma(parseInt($('.calculator-wrap .content-wrap .part:nth-child(2) span.comma')));


    //추가정보 입력
    $('.inputProfile-wrap .content-wrap .input-wrap.interest1 input').on('click', function(){
        $('#selInterestsModal1').css('display', 'flex');
    });
    $('.inputProfile-wrap .content-wrap .input-wrap.interest2 input').on('click', function(){
        $('#selInterestsModal2').css('display', 'flex');
    });
    $('.inputProfile-wrap .content-wrap .input-wrap.country input').on('click', function(){
        $('#selCountryModal').css('display', 'flex');
    });


    //E-7-4 자동배점 신청하기
    $('.E74-apply-wrap .content-wrap .input-wrap.country input').on('click', function(){
        $('#selCountryModal').css('display', 'flex');
    });

    
    // 유학상세
    // 진행단계 확인 modal-서류첨부
    $('#progressModal .info-wrap4 .btn-area span').on('click', function(){
        $('#progressModal .info-wrap4 .upload-wrap').append(
            '<div class="flex-wrap">' +
                '<div class="btn-del">' +
                    '<i></i>' +
                '</div>' +
                '<div class="input-area">' +
                    '<input type="text" placeholder="선택하여 파일 첨부" readonly/>' +
                    '<input type="file" placeholder="" hidden/>' +
                '</div>'+
            '</div>'
        );
    });
    $('#progressModal .info-wrap4 input:text').on('click', function (){
        $(this).next('input:file').click();
    });
    $('#progressModal .info-wrap4').on('click', '.btn-del', function(){
        $(this).parent().remove();
    });


    // 마이페이지
    $('.myPage-wrap .content-wrap > div > .flex-wrap .part.consult .title-area span:nth-child(2)').on('click', function(){
        location.href='/consult'
    });
    $('.myPage-wrap .part.abroad .title-area > span:nth-child(2)').on('click', function(){
        $('#progressModal').css('display', 'flex');
    });
    $('.myPage-wrap .content-wrap .part.company .title-area > div.btn-add span').on('click', function(){
        $('#addEmploymentModal').css('display', 'flex');
    });
    $('.myPage-wrap .content-wrap .part.company .btn-area button').on('click', function(){
        $('#editEmploymentModal').css('display', 'flex');
    });
    $('.myPage-wrap .content-wrap .part.company > div:last-child span').on('click', function(){
        $('#companyHistoryModal').css('display', 'flex');
    });
    $('#companyHistoryModal .modal-wrap > .btn-add').on('click', function(){
        $('#addEmploymentModal').css('display', 'flex');
        // $('#addCompanyHistoryModal').css('display', 'flex');
    });
    if($('.myPage-wrap .content-wrap > div > .flex-wrap .part.abroad > div:nth-child(2)').height() > 450){
        $(this).css({
            'padding-right':'20px'
        });
    }


    // 푸터(footer)
    // 회사소개
    $('.footer-wrap ul.menu li:nth-of-type(1)').on('click', function (){
        location.href='/info'
    });
    // 이용약관
    $('.footer-wrap ul.menu li:nth-of-type(2)').on('click', function (){
        $('#infoServiceModal').css('display', 'flex');
    });
    $('.E74-apply-wrap .check-wrap .check-essential-wrap div:nth-child(1) span').on('click', function (){
        $('#infoServiceModal').css('display', 'flex');
    });
    $('.abroad-apply-wrap .check-wrap .check-essential-wrap div:nth-child(1) span').on('click', function (){
        $('#infoServiceModal').css('display', 'flex');
    });
    // 개인정보처리방침
    $('.footer-wrap ul.menu li:nth-of-type(3)').on('click', function (){
        $('#infoPrivacyModal').css('display', 'flex');
    });
    $('.E74-apply-wrap .check-wrap .check-essential-wrap div:nth-child(2) span').on('click', function (){
        $('#infoPrivacyModal').css('display', 'flex');
    });
    $('.abroad-apply-wrap .check-wrap .check-essential-wrap div:nth-child(2) span').on('click', function (){
        $('#infoPrivacyModal').css('display', 'flex');
    });
    // 이메일무단수집거부
    $('.footer-wrap ul.menu li:nth-of-type(4)').on('click', function (){
        $('#infoEmailModal').css('display', 'flex');
    });


    // 내용 입력 시 border 색상 변화
    // textarea
    let txtareaVal = $('textarea');
    txtareaVal.on('input change onStateChange keypress', function(){
        if($(this).val() == ''){
            $(this).removeClass('complete');
        }else{
            $(this).addClass('complete');
        }
    });
    // 입력필드 내 입력 값이 ''이 아닌 경우 input border 색상 바뀌기
    let inputTxtVal = $('.check input:text');
    inputTxtVal.on('input change onStateChange keypress text', function(){
        if($(this).val() == ''){
            $(this).removeClass('complete');
        }else{
            $(this).addClass('complete');
        }
    });
    // 입력필드 내 입력 값이 ''이 아닌 경우 input border 색상 바뀌기
    let inputTxtVal2 = $('.input-wrap input:text');
    inputTxtVal2.on('input change onStateChange keypress text', function(){
        if($(this).val() == ''){
            $(this).removeClass('complete');
        }else{
            $(this).addClass('complete');
        }
    });

});