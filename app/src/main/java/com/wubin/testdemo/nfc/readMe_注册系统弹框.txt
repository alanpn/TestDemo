

1。权限
    <uses-permission android:name="android.permission.NFC" />

2。任意activity中
    <intent-filter>
        <action android:name="android.nfc.action.NDEF_DISCOVERED" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

    <intent-filter>
         <action android:name="android.nfc.action.TECH_DISCOVERED" />
         <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

    <meta-data android:name="android.nfc.action.TECH_DISCOVERED"
        android:resource="@xml/nfc_tech_filter" />

    <intent-filter>
        <action android:name="android.nfc.action.TAG_DISCOVERED" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

3.res/xml中
    <?xml version="1.0" encoding="utf-8"?>
    <resources xmlns:xliff="urn:oasis:names:tc:xliff:document:1.2">
        <tech-list>
            <tech>android.nfc.tech.MifareClassic</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.MifareUltralight</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.NfcA</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.NfcF</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.Ndef</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.NfcV</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.NfcB</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.NdefFormatable</tech>
        </tech-list>
        <tech-list>
            <tech>android.nfc.tech.IsoDep</tech>
        </tech-list>
    </resources>