# StarrynetHook - Xposed 模块

一个基于 Xposed 框架的 Android Hook 模块，用于修改魅族跨端互联应用（Starrynet）的行为。

## 功能特性

- 🎵 **音乐应用白名单扩展**：自动将 `com.luna.music` 添加到魅族跨端互联应用的音乐应用白名单中
- 🎯 **精准 Hook**：仅针对目标包名 `com.upuphone.starrynet` 进行 Hook，不影响其他应用
- 📝 **详细日志**：提供完整的 Hook 日志输出，方便调试和问题排查

## 技术架构

### 核心实现

- **框架**：Xposed Framework
- **目标包名**：`com.upuphone.starrynet`
- **Hook 类**：`ag.k`
- **Hook 方法**：`d()`
- **Hook 时机**：方法执行后（`afterHookedMethod`）

### 工作原理

1. 模块在应用加载时检查包名，只对目标应用 `com.upuphone.starrynet` 生效
2. Hook `ag.k` 类的 `d()` 方法
3. 在方法执行后，检查返回的 List 对象
4. 如果 List 中不存在 `com.luna.music`，则自动添加
5. 记录操作日志到 Xposed 日志系统

## 项目结构

```
lsp/
└── src/
    └── main/
        └── java/
            └── com/
                └── si9ma/
                    └── linkUnionHook/
                        └── LabelReplacementHook.java  # 主 Hook 类
```

## 环境要求

- Android 设备（已 Root）
- 已安装 Xposed Framework 或 LSPosed
- 目标应用：魅族跨端互联（com.upuphone.starrynet）

## 安装使用

### 1. 编译模块

```bash
# 使用 Gradle 构建项目
./gradlew build
```

### 2. 安装模块

1. 将编译生成的 APK 安装到 Android 设备
2. 在 Xposed Installer 或 LSPosed 中激活模块
3. 勾选作用域为 `com.upuphone.starrynet`
4. 重启设备或重启目标应用

### 3. 验证效果

1. 打开 Xposed 日志或使用 Logcat
2. 查找 `StarrynetHook` 标签的日志
3. 观察是否有 "Added com.luna.music to whiteMusicApps" 的日志输出

## 日志说明

模块提供以下日志输出：

```
StarrynetHook: handleLoadPackage: skip non-target package [包名]
StarrynetHook: handleLoadPackage: attaching hooks for target package
Added com.luna.music to whiteMusicApps
Hook failed: [错误信息]
```

## 开发说明

### 核心代码

```java
public class StarrynetHook implements IXposedHookLoadPackage {
    private static final String TARGET_PACKAGE = "com.upuphone.starrynet";
    
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        // Hook 逻辑实现
    }
}
```

### 扩展功能

如需添加更多音乐应用或其他 Hook 功能，可以：

1. 修改 `afterHookedMethod` 方法
2. 添加更多的 Hook 点
3. 扩展白名单列表

## 注意事项

⚠️ **重要提示**：

1. 此模块仅用于学习和研究目的
2. 请遵守相关法律法规和应用的使用条款
3. Hook 操作可能影响应用稳定性，请谨慎使用
4. 建议在测试设备上进行验证
5. 目标应用更新后，Hook 的类名和方法可能发生变化，需要相应更新

## 故障排除

### Hook 未生效

1. 检查模块是否在 Xposed/LSPosed 中激活
2. 确认作用域已勾选目标应用
3. 查看 Xposed 日志确认是否有错误信息
4. 尝试重启设备

### 应用崩溃

1. 检查目标应用版本是否与 Hook 代码兼容
2. 使用 Logcat 查看详细崩溃信息
3. 临时禁用模块，确认是否为 Hook 导致

## 许可证

本项目仅供学习研究使用。使用本项目代码需遵守相关法律法规。

## 贡献

欢迎提交 Issue 和 Pull Request。

## 联系方式

- 作者：si9ma
- 项目路径：`/Users/si9ma/Factory/FlymeLinkUnion_java/lsp`

---

**免责声明**：本项目仅用于技术研究和学习，使用者需自行承担使用风险。
