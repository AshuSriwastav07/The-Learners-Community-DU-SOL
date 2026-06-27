# PowerShell script to replace the old navbar LinearLayout with BottomNavigationView
# across all layout XML files in the project.

$layoutDir = "e:\AS APK\TheLearnersCommunity\app\src\main\res\layout"

$newNavBar = @"
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNavBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/background_color_night"
        app:itemIconTint="#FF3D00"
        app:itemTextColor="#FF3D00"
        app:labelVisibilityMode="labeled"
        app:menu="@menu/bottom_nav_menu"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
"@

$files = Get-ChildItem $layoutDir -Filter "*.xml" | Where-Object {
    Select-String -Path $_.FullName -Pattern "navbarHome" -Quiet
}

foreach ($file in $files) {
    $content = [System.IO.File]::ReadAllText($file.FullName)
    
    # Pattern: match the LinearLayout block containing navbarHome through its closing tag
    # This handles both the ConstraintLayout variant (with app:layout_constraint attributes)
    # and simpler RelativeLayout/LinearLayout variants
    
    # Find the opening <LinearLayout that contains bottomNavBar id or is the parent of navbarHome
    # We need to match from the LinearLayout open tag (containing bottomNavBar) to its closing </LinearLayout>
    
    $pattern = '(?s)\s*<LinearLayout\s[^>]*android:id="@\+id/bottomNavBar"[^>]*>.*?</LinearLayout>'
    
    if ($content -match $pattern) {
        $content = $content -replace $pattern, "`n$newNavBar"
        [System.IO.File]::WriteAllText($file.FullName, $content)
        Write-Host "Updated: $($file.Name)"
    } else {
        Write-Host "SKIPPED (no match): $($file.Name)"
    }
}
