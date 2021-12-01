# part 1
with open("input") as f:
    lines = f.readlines()
    ctr = 0
    lines = [int(lines[i]) for i in range(len(lines))]
    for i in range(len(lines) - 1):
        if lines[i + 1] > lines[i]:
            print(lines[i], lines[i + 1])
            ctr += 1

    print(ctr)

# part 2
with open("input") as f:
    lines = f.readlines()
    ctr = 0
    lines = [int(lines[i]) for i in range(len(lines))]
    windows = []
    for i in range(len(lines)):
        windows.append(lines[i : i + 3])
    for i in range(len(windows)):
        if len(windows[i]) == 3 and len(windows[i + 1]) == 3:
            if sum(windows[i + 1]) > sum(windows[i]):
                print(windows[i + 1], windows[i])
                ctr += 1

    print(ctr)
