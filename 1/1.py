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
