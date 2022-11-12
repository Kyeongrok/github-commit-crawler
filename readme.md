# GitCommitCrawler
수업중 명렬표에 진도체크 하는 부분을 자동화 시키기 위한 프로젝트

api.github.com/repos/{owner}/{repo}/commits

github.com/{owner}/{repo}/commits

위 두가지 Endpoint중 하나를 호출해서 Commit로그를 보는 기능.

### 사용 방법
이 프로젝트는 api.github.com을 호출 하기 때문에 Environment Variable에 GitToken을 넣어주세요.
넣지 않으면 github.com을 호출 합니다.

토큰을 발급 받은 후 IntelliJ Test Code 실행 전에 아래와 같이 설정 해주어도 됩니다.

![image](https://user-images.githubusercontent.com/1642243/198886054-34692369-93b3-4b9b-befa-ff6b1b4e8945.png)



### Git Token 발급 방법
1. GitHub에 로그인
2. 우측 상단에 프로필 클릭, Setting 클릭
<img src = "https://user-images.githubusercontent.com/1642243/198884841-d6a92d05-6ec5-46e1-ac3f-9f5ee9f0ac35.png" width="50%" height="50%">

3. 좌측 메뉴 가장 아래 Developer setting 클릭
4. 좌측 메뉴에 Personal access tokens - Tokens(classic) 클릭
5. Generate new token - Generate new token (classic) 클릭
6. Note : token의 별칭 기입 ex) CrawlerToken
7. Expiration : 만료기간 설정
8. 선택하지 않아도 api 사용가능. 필요에 따라 선택.
9. 아래 Generate token 클릭
10. 화면에 나온 토큰 문자열은 다시 확인할 수 없으니 잘 보관할 것.

### OAuth.json 필요합니다.

1. Google Sheets API 사용을 위해 dependency 추가가 필요하고 OAuth 발급이 필요합니다. [참조](https://velog.io/@junsugi/Google-Sheet-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0-feat.-Google-API#%EC%84%9C%EB%B9%84%EC%8A%A4-%EA%B3%84%EC%A0%95-%EC%83%9D%EC%84%B1%ED%95%98%EA%B8%B0)

2. 발급 받은 OAuth json 파일이름을 credential.json 으로 변경합니다.

3. 프로젝트 루트 디렉토리 위치에 /google-sheets 폴더를 생성하여 폴더안에 위치시킵니다.

4. 프로젝트를 로컬에서 실행시켜 OAuth 인증 과정을 거칩니다.

5. /google-sheets 폴더 내에 StoredCredential 파일이 생기면 정상적으로 인증된것 입니다.

6. 이 두 파일을 ec2 서버 내 한 폴더에 복사하고 그 폴더를 도커 컨테이너 실행시 컨테이너의 /google-sheets 볼륨과 마운트를 합니다.

### 도커 컨테이너 실행
```bash
docker run -d --name commit-crawler -p 80:8080 \
-e GIT_TOKEN={GitHub Token} \
-v {local volume}:/google-sheets \
git-commits-crawler
```