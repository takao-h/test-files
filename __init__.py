from fabric.api import local

def commit():
    local("./test-files")
    local("git add -A & git commi")
    local("git push origin master")
