# GitCommitCrawler
수업중 명렬표에 진도체크 하는 부분을 자동화 시키기 위한 프로젝트

api.github.com/repos/{owner}/{repo}/commits

github.com/{owner}/{repo}/commits

위 두가지 Endpoint중 하나를 호출해서 Commit로그를 보는 기능.

### 사용 방법
이 프로젝트는 api.github.com을 호출 하기 때문에 Environment Variable에 GitToken을 넣어주세요.
넣지 않으면 github.com을 호출 합니다.

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

Google Sheets API 사용을 위해 dependency 추가가 필요하고 OAuth 발급이 필요합니다.

발급한 OAuth json 파일이름을 credential.json 로 변경하여 src/main/resources/에 저장해야 동작합니다.
