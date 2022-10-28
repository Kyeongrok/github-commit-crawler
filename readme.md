# GitCommitCrawler
수업중 명렬표에 진도체크 하는 부분을 자동화 시키기 위한 프로젝트

api.github.com/repos/{owner}/{repo}/commits

github.com/{owner}/{repo}/commits

위 두가지 Endpoint중 하나를 호출해서 Commit로그를 보는 기능.

### 사용 방법
이 프로젝트는 api.github.com을 호출 하기 때문에 Environment Variable에 GitToken을 넣어주세요.
넣지 않으면 github.com을 호출 합니다.


### Git Token 발급 방법