# 투어헬퍼

* 🔊 프로젝트 소개
  * 국내의 미얀마 유학생 및 근로자를 대상으로 그들의 학적 및 비자를 관리 하기 위해 여행사에서 의뢰한 프로젝트입니다.  
  * 기존 작업자가 DB생성 까지 했던 프로젝트를 그대로 이어 받아 TABLE과 COLUMN명을 그대로 사용하여 작업해야 하는 어려움이 있었습니다.
  * 디자인과 퍼블리싱을 제외한 기존 DB의 수정, 백엔드, 프론트엔드까지 모두 혼자 작업하였습니다.
  * 백엔드는 Restful-API를 개발하고, 프론트엔드는 Ajax로 API를 호출하는 방식으로 개발되었습니다.

* 🏗️개발 기간 및 인원 
  * 2022.09 ~ 2022.11
  * 풀스택 1명, 디자인 퍼블리셔 1명 총 2명 중 풀스택 담당


* 🛠️사용 기술
  * Back-End: Spring Boot, Java, JPA, MySql, QueryDsl
  * Front-End: JavaScript, jQuery


* 📅 DB
  * ERD
![tourHelper_erd](https://github.com/maroowj/tourHelper/assets/77284101/f571bde4-066b-4388-a614-0ba3a5fbd504)


* ✏️ 시나리오
  * 관리자
    * 관리자는 학교, 여행지, 유학생, E-7-4 비자 사전 심사, 상담 내역, 회원 등을 관리 할 수 있습니다.
    * 미얀마 유학생이 국내 유학을 희망하여 신청 할 경우 단계에 맞춰 필요한 서류를 첨부 파일로 받고, 다음 단계로 진행합니다.
    * E-7-4 비자를 발급 받고 싶은 외국인 근로자를 대상으로 사전에 모의 평가를 할 수 있도록 신청을 받고 승인 관리합니다.
        
  * 사용자
    * 유학생은 학교 정보를 조회 할 수 있고, 유학 신청을 할 수 있습니다.
    * 유학 사전 심사 단계별 필요한 서류를 첨부하여 업로드 할 수 있고, 현재 단계를 확인 할 수 있습니다.
    * E-7-4 비자를 발급 받기를 희망하는 근로자는 사전 심사를 신청하여 승인 시 질문의 응답 할 수 있습니다.
    * E-7-4 질문 응답 후 점수를 확인 할 수 있습니다.
    * 국내 외 여행지를 조회하고 관광을 희망 할 시 신청 할 수 있습니다.
   
* 💻구동 화면
  * 사용자
![user_index](https://github.com/maroowj/tourHelper/assets/77284101/50dae914-7526-4768-84f8-7a613927e37b)
![user_school_details](https://github.com/maroowj/tourHelper/assets/77284101/86f5ab36-c86e-405f-b8a6-abb304b4a472)
![user_mypage](https://github.com/maroowj/tourHelper/assets/77284101/1a179aa9-42b0-432c-a8eb-36022452edcc)
![user_abroad](https://github.com/maroowj/tourHelper/assets/77284101/877159ad-1edb-418d-abca-c6cf8607d558)

  * 관리자
![admin_login](https://github.com/maroowj/tourHelper/assets/77284101/19e5b76c-4ed2-4483-b069-94841cf68b19)
![admin_school](https://github.com/maroowj/tourHelper/assets/77284101/c19da61b-4b47-4031-a15c-e75496da9fac)
![admin_visa](https://github.com/maroowj/tourHelper/assets/77284101/caec545f-d4ef-4e18-bf53-c9e73d2994bb)


* 💡부가기능
  * JWT를 사용한 로그인 체계 구축 시도
  * MySql5.7 이상 버전으로 JSON COLUMN 사용하여 데이터 저장
