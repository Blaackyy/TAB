dependencies {
    implementation(projects.shared)
    implementation(libs.bstats.bukkit)
    compileOnly(libs.multipaper)
    compileOnly(libs.placeholderapi)
    compileOnly(libs.vault)
    compileOnly(libs.via)
    compileOnly(libs.authlib)
    compileOnly(libs.essentials) {
        exclude("org.spigotmc", "spigot-api")
    }
    compileOnly(libs.bukkit)
}
