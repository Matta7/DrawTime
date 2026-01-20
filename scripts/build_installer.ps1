$ErrorActionPreference = "Stop"


# =====================
# Configuration
# =====================
$AppName      = "DrawTime"
$MainClass    = "run.Main"
$Vendor       = "Matta"

$ProjectRoot  = Resolve-Path ".."
$TargetDir    = "$ProjectRoot/target"
$DistDir      = "$ProjectRoot/dist"
$RuntimeDir   = "$ProjectRoot/runtime"
$IconPath     = "$ProjectRoot/src/main/resources/icon.ico"


# =====================
# Check environment
# =====================
if (-not $env:JAVA_HOME) {
    Write-Error "JAVA_HOME not found"
}

if (-not (Test-Path $IconPath)) {
    Write-Error "Icon not found : $IconPath"
}


# =====================
# Get version from pom.xml
# =====================
[xml]$Pom = Get-Content "$ProjectRoot/pom.xml"
$AppVersion = $Pom.project.version

if (-not $AppVersion) {
    throw "Impossible de lire la version depuis pom.xml"
}

$JarName = "$AppName-$AppVersion.jar"

Write-Host "Version : $AppVersion"


# =====================
# Build Maven
# =====================
Write-Host "Build Maven..."
Push-Location ..
mvn clean package
Pop-Location


# =====================
# Dist repertory
# =====================
if (Test-Path $DistDir) {
    Remove-Item -Recurse -Force $DistDir
}
New-Item -ItemType Directory -Path $DistDir | Out-Null


# =====================
# Java runtime creation (jlink)
# =====================
if (Test-Path $RuntimeDir) {
    Remove-Item -Recurse -Force $RuntimeDir
}

Write-Host "Création du runtime Java..."

jlink `
  --module-path "$env:JAVA_HOME/jmods" `
  --add-modules java.base,java.desktop `
  --strip-debug `
  --no-header-files `
  --no-man-pages `
  --compress=2 `
  --output $RuntimeDir


# =====================
# EXE Installer Generation (jpackage)
# =====================
Write-Host "EXE installer generation..."

jpackage `
  --type exe `
  --dest $DistDir `
  --name $AppName `
  --app-version $AppVersion `
  --description $AppName `
  --vendor $Vendor `
  --icon $IconPath `
  --input $TargetDir `
  --main-jar $JarName `
  --main-class $MainClass `
  --runtime-image $RuntimeDir `
  --win-dir-chooser `
  --win-menu `
  --win-shortcut `


# =====================
# Clean
# =====================
Remove-Item -Recurse -Force $RuntimeDir

Write-Host "Installer generated"
