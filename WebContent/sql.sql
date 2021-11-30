create table uploadboard (
    num number primary key,
    description varchar2(4000) not null,
    filename varchar2(255) not null,
    uploader varchar2(100) not null,
    regdate timestamp default sysdate not null,
    readcount number default 0 not null,
    password varchar2(250) not null );
    
create sequence upload_seq start with 1 increment by 1 nomaxvalue nocache nocycle;
commit;

-- C: 아래에 upload 라는 디렉터리 생성 ( 업로드한, 다운로드할 자료가 위치하는 프로젝트 외부 경로)
-- Servers>context.xml 에서 이 경로와 마운트 설정하기 