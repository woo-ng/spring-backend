all:
	gradlew build
install:
	powershell -Command "iwr https://fly.io/install.ps1 -useb | iex"
