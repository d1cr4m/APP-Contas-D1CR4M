#!/data/data/com.termux/files/usr/bin/bash
set -e
cd "$(dirname "$0")"
SENHA="${SENHA:-weed1326}"
pkg install -y openjdk-17 aapt apksigner dx ecj >/dev/null 2>&1 || true
AJ="$PWD/android.jar"
[ -f "$AJ" ] || curl -fL -o "$AJ" https://github.com/Sable/android-platforms/raw/master/android-29/android.jar
rm -rf build obj; mkdir -p build obj
[ -f contas.jks ] || keytool -genkeypair -keystore contas.jks -storepass "$SENHA" -keypass "$SENHA" -alias k -keyalg RSA -keysize 2048 -validity 10000 -dname "CN=Contas"
aapt package -f -M AndroidManifest.xml -S res -A assets -I "$AJ" -F build/app.apk
ecj -nowarn -cp "$AJ" -d obj $(find src -name '*.java')
dx --dex --output=build/classes.dex obj
(cd build && aapt add app.apk classes.dex)
apksigner sign --ks contas.jks --ks-pass pass:"$SENHA" --key-pass pass:"$SENHA" --out contas.apk build/app.apk
cp contas.apk /sdcard/Download/contas.apk 2>/dev/null && echo "Copiado para Download/contas.apk"
echo "OK: $(pwd)/contas.apk"
